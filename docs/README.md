# Jack User Guide

![1771537395011](images/README/1771537395011.png)

Jack is a chatbot app that remembers your tasks. By remembering only a few simple commands, it offers a fast easy, yet effective way of keeping track your busy schedule! Read through this page for how you can interact with Jack.

## Adding Todo

Adds a `todo` task.

Format: `todo DESCRIPTION`

Example: `todo CS2103T course survey`

Successful usage will produce a acknowlegement prompt

```
Awesome! I've added this task:
...
You're doing great! Keep going!
```

## Adding Deadline

Adds a `deadline` task.

Format: `deadline DESCRIPTION /by DATETIME`

- Datetime should be in the format dd/mm/yyyy HHmm
- Other formats may work, please check the response to see whether the chatbot recognises your command
- Time defaults to midnight if no time is specified

Example: `deadline CS2101 CA2 /by 01/03/2026`

Successful usage will produce a acknowlegement prompt

```
Got it! New Deadline added:
...
Don't worry, I'll remind you before it's due!
```

## Adding Event

Adds a `event` task.

Format: `event DESCRIPTION /from DATETIME /to DATETIME`

- Datetime should be in the format dd/mm/yyyy HHmm
- Other formats may work, please check the response to see whether the chatbot recognises your command
- Time defaults to midnight if no time is specified

Example: `event CS2103T project /from 01/02/2026 /to 22/02/2026`

Successful usage will produce a acknowlegement prompt

```
Got it! Event added! Looking forward to it:
...
```

## Listing All Tasks

Shows the full list of `tasks`.

Format: `list`

Example:`list` `deadline CS2101 CA2 /by 01/03/2026`

Successful usage will produce a acknowlegement prompt

```
Here's your task list! You can do this!
...
```

## Marking Task

Adds a completed `mark` to a task.

Format: `mark INDEX`

- Index refers to the unique index assigned to the task when it was added
- Index is assigned to task by creation order
- Index must be a positive integer

Example: `mark 6`

Successful usage will produce a acknowlegement prompt

```
Wow! You completed a task!
You're amazing! Here's what you finished:
...
Celebrate this small win!
```

## Unmarking Task

removes a completed `mark` to a task.

Format: `unmark INDEX`

- Index refers to the unique index assigned to the task when it was added
- Index is assigned to task by creation order
- Index must be a positive integer

Example: `unmark 5`

Successful usage will produce a acknowlegement prompt

```
No worries! i've unmarked this task for you:
...
Take your time, no rush!
```

## Undo Task

Removes the latest added `task`.

Format: `undo`

Example: `undo`

Successful usage will produce a acknowlegement prompt

```
No problem! I've undone that for you
It's okay to change your mind - we all do!
...
```

## Finding Task

Finds a  `task` by matching keyword with what is stored.

Format: `find KEYWORD`

- Keyword is case-sensitive
- Looks for substring exact matches only in description

Example: `find survey`

Successful usage will produce a acknowlegement prompt

```
I found these for you!
...
```

## Delete Task

Removes a `task` remembered by the chatbot.

Format: `delete INDEX`

- Index refers to the unique index assigned to the task when it was added
- Index is assigned to task by creation order
- Index must be a positive integer

Example: `delete 5`

Successful usage will produce a acknowlegement prompt

```
Alright! Iv'e removed this task for you:
...
Sometimes letting go is the best choice!
```

## Exiting

Exits the program.

Format: `bye`

- The program close itself after inputting the command

Example: `bye`

Successful usage will produce a acknowlegement prompt, followed by closure of the program

```
Bye bye!
It was nice chatting with you!
Come back soon, okay?
```

## Saving

The chatbot will forever remembember your tasks - even if u close it provided you close it properly. Killing the program manually (i.e. without inputting `bye` will cause the chatbot to forget about all the task since the ssame session that you opened the program.
