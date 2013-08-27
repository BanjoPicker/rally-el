rally-el
========

An implementation of an interview question for Rally Software.

The question is about implementing a templating engine.  

For this, I will use the construct ${...} to lookup 
properties in a map-like structure.  The rules will be that $ is an escape character, and 
inside the ${ } leading and trailing whitespace will be ignored.  That is, ${  foo } will resolve to the same thing ${foo}.
An exception will thrown if any lookup fails an no output will be returned.

The test strategy will be to use several properties files with the properties defined as well 
as a template property and an output property.  For example, 

# Sample properties file:
template=Hello ${user}!
user=World
output=Hello World!

could be a file under src/test/resources called test01.properties.  We can run this to verify the desired output appears.
