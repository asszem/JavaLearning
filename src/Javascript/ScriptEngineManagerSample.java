package Javascript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/*
 * http://docs.oracle.com/javase/6/docs/technotes/guides/scripting/programmer_guide/index.html
 * */
public class ScriptEngineManagerSample {

	public static void main(String[] args) {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		try {
			engine.eval("print('Hello, World')");
			String script = "var obj = new Object(); obj.hello = function(name) { print('Hello, ' + name); }";
			engine.eval(script);
			
			// javax.script.Invocable is an optional interface.
	        // Check whether your script engine implements or not!
	        // Note that the JavaScript engine implements Invocable interface.
	        Invocable inv = (Invocable) engine;

	        // get script object on which we want to call the method
	        Object obj = engine.get("obj");

	        // invoke the method named "hello" on the script object "obj"
	        inv.invokeMethod(obj, "hello", "Script Method !!" );
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
