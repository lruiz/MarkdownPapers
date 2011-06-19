MarkdownPapers and doxia
========================

[Doxia] is a content generation framework, supporting a variety of markup languages, MarkdownPapers 
provides a doxia module that enables writing content in markdown format.


Write all your docs in **src/site/markdown/** with **md** as file extension, then configure your 
maven-site-plugin and include markdownpapers-doxia-module as dependency.

Take a look at the [site source code][src].

    ...
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-site-plugin</artifactId>
        <version>3.0-beta-3</version>
        <configuration>
            <reportPlugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-project-info-reports-plugin</artifactId>
                    <version>2.2</version>
                    <configuration>
                        <dependencyDetailsEnabled>false</dependencyDetailsEnabled>
                        <dependencyLocationsEnabled>false</dependencyLocationsEnabled>
                    </configuration>
                    <reports>
                        <report>license</report>
                        <report>project-team</report>
                    </reports>
                </plugin>
            </reportPlugins>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>org.tautua.markdownpapers</groupId>
                <artifactId>markdownpapers-doxia-module</artifactId>
                <version>${use latest}</version>
            </dependency>
        </dependencies>
    </plugin>
    ...


[Doxia]: http://maven.apache.org/doxia/doxia/index.html
[src]: https://github.com/lruiz/MarkdownPapers/tree/master/www/src/site/markdown
