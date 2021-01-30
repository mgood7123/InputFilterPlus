# Input Filter Plus

an `InputFilter` which offers a vast array of callbacks in place of `InputFilter#filter`

# Classes

Class Name        | Class Description
----------------- | -----------------
InputFilterPlus   | the core class, where all the hard work is done
BackSpaceFilter   | a version of `InputFilterPlus` that can disallow backspaces
ReplacementFilter | a version of `InputFilterPlus` that can replace text
SpaceFilter       | a version of `InputFilterPlus` that can disallow spaces. this is actually just a wrapper around `ReplacementFilter`

# Callback return values

these should be self explanatory

all callbacks accept either `PROCESSES_MODE_APPEND_ORIGINAL` or `PROCESSES_MODE_APPEND_NOTHING`

# Callbacks

### Append

* appended letter (start)
* appended letter (middle)
* appended letter (end)

### Remove

* removed letter (start)
* removed letter (middle)
* removed letter (end)

* removed string (start)
* removed string (middle)
* removed string (end)

### Replace

* replaced letter (start)
* replaced letter (middle)
* replaced letter (end)

* replaced string (start)
* replaced string (middle)
* replaced string (end)

### Paste

* pasted letter (start)
* pasted letter (middle)
* pasted letter (end)

* pasted string (start)
* pasted string (middle)
* pasted string (end)

# Notes

### Note

for some unknown reason, the `select text, and then press space`

is interpreted not as a single `InputFilter#filter` event `replaced string`

but instead as two sequential `InputFilter#filter` events:

* `removed string` followed by `appended letter`

### Note

text that contains spaces behaves in a non conforming way with `InputFilter#filter`

### Note

since `pasting text`, and `glide/swipe text input`, emit the same functionality,
it is impossible to distinguish between the two

for example:

* glide/swipe  : `source = [focus], start = [0], end = [5], dest = [lll], dstart = [2], dend = [2]`
* paste        : `source = [focus], start = [0], end = [5], dest = [lll], dstart = [2], dend = [2]`

for this reason it is also impossible to append a string since
this functionality is equivalent to `paste string`

this is also true for `remove string` and `cut string`