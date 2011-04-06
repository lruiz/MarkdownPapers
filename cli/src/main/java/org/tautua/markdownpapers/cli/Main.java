/*
 * Copyright 2011, TAUTUA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers.cli;

import org.apache.commons.cli.*;
import org.tautua.markdownpapers.Markdown;

import java.io.*;
import java.util.*;

/**
 * @author Larry Ruiz
 */
public class Main {
    private static final String DEFAULT_INPUT_EXTENSION = ".md";
    private static final String OUTPUT_EXTENSION = ".html";

    private CommandLine commandLine;
    private Options options;
    private boolean badUsage;
    private File input;
    private File outputDirectory;

    private Writer consoleOut;


    public Main(String[] arguments) throws Exception {
        this(arguments, new OutputStreamWriter(System.out));
    }


    public Main(String[] arguments, Writer consoleOut) throws Exception {
        CommandLineParser parser = new PosixParser();
        options = getOptions();
        commandLine = parser.parse(options, arguments);
        this.consoleOut = consoleOut;
        badUsage = commandLine.getArgList().isEmpty();
    }

    private static Options getOptions() {
        Options options = new Options();
        options.addOption("o", true, "output directory");
        options.addOption("r", false, "recursive");
        options.addOption("h", false, "help");
        return options;
    }

    public void execute() throws Exception {
        if (badUsage) {                 
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("mdpapers <input> [options]", options);
        } else {
            String inputArg = commandLine.getArgs()[0];
            input = ((inputArg == null) ? new File(".") : new File(inputArg));

            String outputArg = commandLine.getOptionValue("o");
            outputDirectory = ((outputArg == null) ? new File(".") : new File(outputArg));

            Collection<File> files = searchInputs();
            if (files.isEmpty()) {
                consoleOut.write("file or directory does not exist.\n");
            } else {
                for (File markdown : files) {
                    transform(markdown);
                }
            }
        }
        consoleOut.flush();
    }

    private Collection<File> searchInputs() {
        return searchInputsAt(input);
    }

    private Collection<File> searchInputsAt(File lookAt) {
        if (!lookAt.exists()) {
            return Collections.emptyList();
        } else if (lookAt.isFile()) {
            return Arrays.asList(lookAt);
        }

        Collection<File> cache = new ArrayList<File>();
        for (File candidate : lookAt.listFiles()) {
            if (candidate.isDirectory() && commandLine.hasOption("r")) {
                cache.addAll(searchInputsAt(candidate));
            } else if (candidate.getName().endsWith(DEFAULT_INPUT_EXTENSION)){
                cache.add(candidate);
            }
        }

        return cache;
    }

    public void transform(File markdown) throws Exception {
        Reader in = new FileReader(markdown);
        Writer out = new FileWriter(getOutputFile(markdown));
        transform(in, out);
        out.flush();
        out.close();
    }

    private File getOutputFile(File markdown) {
        return new File(markdown.getAbsolutePath().substring(0, markdown.getAbsolutePath().lastIndexOf("."))
                + OUTPUT_EXTENSION);
    }

    public void transform(Reader in, Writer out) throws org.tautua.markdownpapers.parser.ParseException {
        Markdown md = new Markdown();
        md.transform(in, out);
    }

    public static void main(String[] arguments) throws Exception {
        new Main(arguments).execute();
    }
}
