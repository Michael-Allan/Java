package Java;


/** Thrown to indicate an interrupt left unhandled because the only known source of interrupts
  * is the operating system (e.g. via user `Ctrl-C` and `SIGINT`) whose interrupts
  * already the VM launcher (e.g. the `java` command) handles.
  *
  *     @see <a href='https://docs.oracle.com/en/java/javase/15/docs/specs/man/java.html'>
  *       The `java` command</a>
  */
public class UnsourcedInterrupt extends Unhandled {


    /** @see #getCause()
      */
    public UnsourcedInterrupt( InterruptedException cause ) { super( cause ); }}


                                                   // Copyright © 2020-2021  Michael Allan.  Licence MIT.
