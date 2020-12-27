package Java;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;


/** An attribution of thread safety.  The target element may safely be used by multiple processes
  * running in parallel threads.  Such use will not of itself give rise to concurrency faults. */

  @Documented @Retention(SOURCE)
  @Target({ CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PARAMETER, TYPE_USE }) // [OTT]
    // A target of `MODULE` or `PACKAGE` would be unclear, both in meaning and utility.
public @interface AsyncSafe {}


// NOTE
// ────
//   OTT  Other ‘TYPE’ targets would be redundant with `TYPE_USE`.
//        https://stackoverflow.com/questions/65443843



                                                        // Copyright © 2020  Michael Allan.  Licence MIT.
