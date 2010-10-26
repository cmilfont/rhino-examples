package org.milfont.rhinoexamples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class RhinoEngine {

	public static void main(String[] args) {
		RhinoEngine rhinoEngine = new RhinoEngine();
	}
	
	public RhinoEngine() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = mgr.getEngineFactories();
		for (ScriptEngineFactory factory : factories) {
			System.out.println("ScriptEngineFactory Info");
			String engName = factory.getEngineName();
			String engVersion = factory.getEngineVersion();
			String langName = factory.getLanguageName();
			String langVersion = factory.getLanguageVersion();
			System.out
					.printf("\tScript Engine: %s (%s)\n", engName, engVersion);
			List<String> engNames = factory.getNames();
			for (String name : engNames) {
				System.out.printf("\tEngine Alias: %s\n", name);
			}
			System.out.printf("\tLanguage: %s (%s)\n", langName, langVersion);
		}

		ScriptEngineFactory rhino = mgr.getEngineByName("rhino-nonjdk").getFactory();
		ScriptEngine eng = rhino.getScriptEngine();
		try {
			String path = getClass().getResource("teste.js").getFile();
			eng.eval(new FileReader(path));
			
			String path2 = getClass().getResource("teste2.js").getFile();
			eng.eval(new FileReader(path2));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		System.out.println(eng.toString());
	}
	
	public static String executeHelloRhino()  {
        // Create and enter a Context. A Context stores information about the execution environment of a script.
        Context cx = Context.enter();

        try {
            // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
            // executed. The null parameter tells initStandardObjects 
            // to create and return a scope object that we use
            // in later calls.
            Scriptable scope = cx.initStandardObjects();

            // Build the script
            String script = "var s = 'Hello, Rhino'; s;";

            // Execute the script
            Object obj = cx.evaluateString( scope, script, "TestScript", 1, null );
            System.out.println( "Object: " + obj );

            // Cast the result to a string
            return ( String )obj;

        } catch( Exception e ) {
            e.printStackTrace();
        }
        finally {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
            Context.exit();
        }
        return null;
    }

}
