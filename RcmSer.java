import java.io.*;
import java.net.*;
import java.io.InputStream;
import java.lang.Runtime;
import java.lang.Object;
public class RcmSer
{
public static void main(String args[])throws IOException
{
	ServerSocket s=new ServerSocket(1254);
	      Socket s1=s.accept();
	InputStream is=s1.getInputStream();
	DataInputStream dis=new DataInputStream(is);
	OutputStream os=s1.getOutputStream();
	DataOutputStream dos=new DataOutputStream(os);
	String cmd[]=new String[3];
	cmd[0]="/bin/sh";
	cmd[1]="-c";
	cmd[2]=dis.readUTF();
	//String cmd=new String(dis.readUTF());
	Runtime r=Runtime.getRuntime();
	Process a=null;
	try
	{
		a=r.exec(cmd);
		System.out.println("executing the command:"+cmd);
		InputStream error=a.getErrorStream();
		//System.out.println("error!!!!"+error);
		BufferedReader inp=new BufferedReader(new InputStreamReader(a.getInputStream()));
		String line;
		String k="";
		while((line=inp.readLine())!=null)
		{
			//System.out.println(line);
			k=k+line+"\n";
		}
		dos.writeUTF(k);
		int exval=a.exitValue();
		dos.writeUTF(""+exval);
		//System.out.println("exit with error"+exval);
		//String pass=new String(dis.readUTF());
		//System.out.println(pass);
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
		dis.close();
		is.close();
		dos.close();
		os.close();
		s1.close(); 
		s.close();
}

}
