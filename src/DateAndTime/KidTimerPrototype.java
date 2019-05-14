package DateAndTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KidTimerPrototype {

	public static String getLoginTime() throws IOException, InterruptedException {
		StringBuilder sb = new StringBuilder();
		try {
			Process p;

			p = Runtime.getRuntime().exec("quser");
			p.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println("Runtime = " + sb);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

	public static String[] runPowerShellCommand(String command) {
		// Executing the command
		String[] result = new String[2];
		System.out.println("Command: " + command);
		Process powerShellProcess;
		try {
			powerShellProcess = Runtime.getRuntime().exec(command);
			powerShellProcess.getOutputStream().close();
			String line;
			System.out.println("Standard Output:");
			BufferedReader stdout = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
			StringBuilder outputResult = new StringBuilder();
			while ((line = stdout.readLine()) != null) {
				System.out.println(line);
				outputResult.append(line);
			}
			stdout.close();
			System.out.println("Standard Error:");
			StringBuilder outputErrorResult = new StringBuilder();
			BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
			while ((line = stderr.readLine()) != null) {
				System.out.println(line);
				outputErrorResult.append(line);
			}
			stderr.close();
			System.out.println("Done");
			result[0] = outputResult.toString();
			result[1] = outputErrorResult.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// TODO return a Date object instead of string
	public static String[] getProcessStartTime(String processName) {
		String command = "powershell.exe Get-Process | Select-Object starttime, name  | findstr "+processName;
		String result[] = new String[2];
		result = runPowerShellCommand(command);
		result[0]=result[0].substring(12, 16);
		return result;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		getLoginTime();
		String command = "powershell.exe  $PSVersionTable.PSVersion";
		runPowerShellCommand(command);
		String processName="notepad";
		String[] result=new String[2];
		result=getProcessStartTime(processName);
		System.out.println(result[0]);
	}

}
