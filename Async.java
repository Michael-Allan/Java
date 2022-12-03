package Java;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;


/** An indication that the target element has one or more asynchronous properties,
  * which should be detailed in its API description.
  */
  @Documented @Retention(SOURCE)
  @Target({ CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, MODULE, PACKAGE, PARAMETER, RECORD_COMPONENT,
   TYPE_USE }) // [OTT]
public @interface Async {}


// NOTE
// ────
//   OTT  Other ‘TYPE’ targets would be redundant with `TYPE_USE`.
//        https://stackoverflow.com/questions/65443843



                                                  // Copyright © 2020, 2022  Michael Allan.  Licence MIT.
