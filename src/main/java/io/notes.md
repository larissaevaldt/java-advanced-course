# IO
* Read and write console and file data using I/O Streams.
* "Java I/O fundamentals" *java.io* package

## Java IO Fundamentals
* Java reads/writes characters or bytes.
* Classes with "Stream" in their name read/writes **binary**
  * FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream
  * used for images and executable files
* *Readers* and *Writers* are used to read/write **text**
  * FileReader, BufferedReader, FileWriter, BufferedWriter, PrintWriter
  * used for text files i.e. character or string data
* *File* is the class that enables you create objects from which you can then create actual physical files on the hard disk.
* Many of the previous classes are intended to be *wrapped*. This enables low-level classes to get access to higher-level functionality. This is for efficiency e.g. buffering.

| Abstract Classes | Low-Level classes                          | High-level classes (for efficiency) | High-level classes (Other)                                                                     | Example                                                                                                             |
|------------------|--------------------------------------------|-------------------------------------|------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| InputStream      | FileInputStream (reads one byte at a time) | BufferedInputStream                 | ObjectInputStream                                                                              | new ObjectInputStream(new FileInputStream("f.dat");                                                                 |
| OutputStream     | FileOutputStream                           | BufferedOutputStream                | ObjectOutputStream <br/> PrintStream                                                           | new ObjectOutputStream(new FileOutputStream("f.dat");                                                               |
| Reader           | FileReader                                 | BufferedReader                      | InputStreamReader <br/> (bridge between byte streams and character streamer)                   | new BufferedReader(new FileReader("in.txt"); <br/> new BufferedReader(new InputStreamReader(System.in); //keyboard  |
| Writer           | FileWriter                                 | BufferedWriter                      | PrintWriter <br/> OutputStreamWriter <br/> (bridge between character streams and byte streams) | new BufferedWriter(new FileWriter("in.txt"); <br/> new BufferedWriter(new OutputStreamReader(System.out); //console |

