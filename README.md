# Input Filter Plus

an InputFilter which offers a vast array of callbacks in place of `InputFilter#filter`

# Callback return values

these should be self explanatory

all callbacks accept either `PROCESSES_MODE_APPEND_ORIGINAL` or `PROCESSES_MODE_APPEND_NOTHING`

# Callbacks

* appended letter (start)
* appended letter (middle)
* appended letter (end)

* removed letter (start)
* removed letter (middle)
* removed letter (end)

* removed string (start)
* removed string (middle)
* removed string (end)

* replaced letter (start)
* replaced letter (middle)
* replaced letter (end)

* replaced string (start)
* replaced string (middle)
* replaced string (end)

* pasted letter (start)
* pasted letter (middle)
* pasted letter (end)

* pasted string (start)
* pasted string (middle)
* pasted string (end)

# Notes

for some unknown reason, the `select text, and then press space`
is interpreted not as a `replaced string`,
but instead as a `removed string` followed by an `appended letter`

text that contains spaces behaves in a non conforming way with `filter`

# Classes

Class Name        | Class Description
----------------- | -----------------
InputFilterPlus   | the base class, where all the callbacks are
BackSpaceFilter   | a version of `InputFilterPlus` that can disallow backspaces
ReplacementFilter | a version of `InputFilterPlus` that can replace text
SpaceFilter       | a version of `InputFilterPlus` that can disallow spaces. this is actually just a wrapper around `ReplacementFilter`
