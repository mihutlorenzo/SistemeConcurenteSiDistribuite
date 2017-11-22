import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyThread implements Runnable {
	private int min;
	private int max;
	private final String value = "FFAF1F9AF569B507FEF3F7D5EA078BD2DFCF1B6972C1DADC3483DF9B4A71EAAE";
	private int threadIndex;

	public MyThread(int min, int max, int threadIndex) {
		super();
		this.min = min;
		this.max = max;
		this.threadIndex = threadIndex;

	}

	@Override
	public void run() {
		String result = "";
		for (int i = min; i < max && (Application.status == false); i++) {
			try {
				result = HashWithJavaMessageDigest("" + i);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (result.equalsIgnoreCase(value)) {
				Application.status = true;
				System.out.println("The password is " +i );
				System.out.println("Password found by thread with number " + threadIndex);

			}

		}
	}

	private String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public String HashWithJavaMessageDigest(String originalString) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);

	}

}
