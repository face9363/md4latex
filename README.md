# LaTeX to markdown compiler

**convert extended markdown syntax to compilable latex file.**

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

\seciton{hello!}

\begin{quote}
Hirkoki
Niceto Meet you!
\end{quote}

\begin{gather}
f(x) = x^2
\end{gather}

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
| \[great!](img/src.jpg) | \begin{figure}[H]   \centering   \includegraphics[width=8cm]{img/src.jpg}   \caption{great!} \end{figure} |

### Table 

**not been suppported** :cry:

## Other function

- auto dependency injection
- auto include partial tex file(see example)

