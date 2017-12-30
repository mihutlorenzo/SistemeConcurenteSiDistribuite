Let's say your file is in E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks

Run Command Prompt
use cd .. until appear C:\>
use e: to change partition

E:\> cd E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src
This makes E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src the current directory.

E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src dir
This displays the directory contents. You should see OneLaneBridge.java among the files.

E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src set path=%path%;C:\Program Files\Java\jdk1.8.0_121\bin
This tells the system where to find JDK programs.

E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src javac *.java
This runs javac.exe, the compiler. The .java file are compiled into . class file.

E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src dir
 You should see *.java and *.class among the files.

E:\My work\Sisteme_Concurente_Si_Distribuite\BridgeProblemUsingLocks\src java OneLaneBridge x y
x->specify number of cars you have that represent threads
y->specify number of turn to pass the bridge

The program will run and you will se the outcome.