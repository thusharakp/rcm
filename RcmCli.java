import java.io.*;
import java.net.*;
public class RcmCli
{
	public static void main(String args[])throws IOException
	{
		Socket s1=new Socket("192.168.0.121",1254);
		OutputStream os=s1.getOutputStream();
		DataOutputStream dos=new DataOutputStream(os);
		InputStream is=s1.getInputStream();
		DataInputStream dis=new DataInputStream(is);
		InputStreamReader isr=new InputStreamReader(System.in);
		System.out.println("enter the command:");
		BufferedReader br=new BufferedReader(isr);
		String cmd=br.readLine();
		dos.writeUTF(cmd);
		String rep=new String(dis.readUTF());
		String err=new String(dis.readUTF());
		System.out.println(rep);
		System.out.println("exit with error"+err);
		//String pass=br.readLine();
		//dos.writeUTF(pass);
		dos.close();
		os.close();
		is.close();
		dis.close();
		s1.close(); 
	}
}
