What is MarkdownPapers?
=======================

MarkdownPapers is a java implementation of Markdown language created by [John Gruber] which provides
an easy-to-read, easy-to-write plain text format that takes many cues from existing conventions for
marking up plain text in email.

This implementation consist in parser built from a [JAVACC] grammar definition that produces an
abstract syntax tree ([AST]) where transformations are performed following the [Visitor Pattern].

Quick example
-------------

The most prominent component would be org.tautua.markdownpapers.Markdown which receives a java.io.Reader
(input content) and java.io.Writer (output).

    Reader in = new FileReader("in.md");
    Writer out = new FileWriter("out.html");

    Markdown md = new Markdown();
    md.transform(in, out);

And if you need to handle the AST by your own...

    Reader in = new FileReader("in.md");
    Visitor v = new HtmlEmitter();
    Parser parser = new Parser(in);

    Document doc = parser.parse();
    doc.accept(v);

Maven dependency
----------------

You can add markdonwpapers to your project as dependency for immediate use :).

    <dependency>
        <groupId>org.tautua.markdownpapers</groupId>
        <artifactId>markdownpapers-core</artifactId>
        <version>${use latest}</version>
    </dependency>


[John Gruber]: http://daringfireball.net/projects/markdown
[JAVACC]: http://javacc.java.net
[AST]: http://en.wikipedia.org/wiki/Abstract_syntax_tree
[Visitor Pattern]: http://en.wikipedia.org/wiki/Visitor_pattern
