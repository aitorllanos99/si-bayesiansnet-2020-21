/*
 * Copyright (c) CISIAD, UNED, Spain,  2019. Licensed under the GPLv3 licence
 * Unless required by applicable law or agreed to in writing,
 * this code is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OF ANY KIND.
 */

package org.openmarkov.full;

import org.openmarkov.gui.configuration.ComponentConfiguration;
import org.openmarkov.gui.configuration.OpenMarkovConfiguration;
import org.openmarkov.gui.localize.StringDatabase;
import org.openmarkov.gui.window.MainGUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class stores a set of additionalProperties and the <code>main</code>
 * method.
 * <p>
 * If there is some other main method in other class is only for test.
 * <p>
 *
 * @author manuel
 * @author fjdiez
 * @author jmendoza
 * @version 1.1 jlgozalo - Suppress public modifier in the configuration
 * attributes (not required); add explicit initial value and fix bug in
 * the getUniqueInstance with the mainGui starting inside the singleton
 * (not outside) to prevent double GUI initialization
 * @since OpenMarkov 1.0
 */
public class OpenMarkov {
	// Attributes
	/**
	 * Stores variables such as initialPath, netsDirectory ...
	 */
	ComponentConfiguration openMarkovKernelConfiguration = null;
	/**
	 * Stores the configuration of each component.
	 */
	OpenMarkovConfiguration openMarkovConfiguration = null;

	/**
	 * OpenMarkov main class
	 *
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		List<String> filesToOpen = new ArrayList<String>();
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("-l") || args[i].equals("-language")) {
				if (i + 1 < args.length) {
					StringDatabase.getUniqueInstance().setLanguage(args[i + 1]);
					++i;
				}
			} else if (new File(args[i]).exists()) {
				filesToOpen.add(args[i]);
			}
		}
		MainGUI openMarkovGUI = new MainGUI();
		openMarkovGUI.setVisible(true);
		for (String filename : filesToOpen) {
			openMarkovGUI.openNetwork(filename);
		}
	}
}