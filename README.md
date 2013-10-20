rally-el
========

An implementation of an interview question for Rally Software.

The question is about implementing a templating engine.  

For this, I will use the construct ${...} to lookup 
properties in a map-like structure.  The rules will be that $ is an escape character, and 
inside the ${ } leading and trailing whitespace will be ignored.  That is, ${  foo } will resolve to the same thing ${foo}.
An exception will thrown if any lookup fails an no output will be returned.

See the test of the default engine for typical ways to use this.
