# LaTeX to markdown compiler

**convert extended markdown syntax to compilable latex file.**

<img src="https://static.face9363.net/static/img/icons/md4latex_black.svg" alt="logo" width="300"/>

see example:
```tex:input.tex

# hello!

>Hirkoki
Niceto Meet you!

$$
f(x) = x^2
$$

```
is converted to 

```tex:output.tex

\section{hello!}

\begin{quote}
Hirkoki
Niceto Meet you!
\end{quote}

\begin{gather}
f(x) = x^2
\end{gather}

```

## quick use
```
$ wget https://github.com/face9363/md4latex/releases/download/0.1/md4tex.jar
$ chmod 777 md4tex.jar
$ ./md4tex.jar path/to/yourfile
```

## compile from source
```
$ javac -sourcepath ./ -d classes src/Main.java
$ jar cvfm md4tex.jar MATE-INFO/MANIFEST.MF -C classes .
$ java -jar md4tex.jar path/to/yourfile
```

## usage

### Headline
| md     | tex              |
|--------|------------------|
| \#     | \section{}       |
| \##    | \subsection{}    |
| \###   | \subsubsection{} |
| \####  | \paragraph{}     |
| \##### | \subparagraph{}  |

### Math
| md         | tex                              |
|------------|----------------------------------|
|   \$f(x)$   | \$f(x)$                           |
| \$\$f(x)$$   | \begin{gather} f(x) \end{gather} |
| \$\$\$f(x)$$$ | \begin{align} f(x) \end{align}   |

### Quote
| md         | tex                              |
|------------|----------------------------------|
|   \>Hello  | \begin{quote} Hello \end{quote}  |


### Code
| md         | tex                              |
|------------|----------------------------------|
| \``` print("Hello") \``` | \\begin{lstlisting} print("Hello") \end{lstlisting} |

### Image
| md                    | tex                                                                                                       |
|-----------------------|-----------------------------------------------------------------------------------------------------------|
| \!\[great](img/src.jpg_lab) | \begin{figure}[H]   \centering   \includegraphics[width=8cm]{img/src.jpg}   \caption{great!} \label{lab} \end{figure} |

### Table 

**not been suppported** :cry:

## Other function

- auto dependency injection
- auto include partial tex file(see example)

