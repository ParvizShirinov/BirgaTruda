import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main {

	public static void main(String[] args) {
		 Frame f = new Birga("����� �����");//������� ���� �����
	        f.setResizable(false);			//�������� ������ ������
	        f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent ev) {
	                System.exit(0);			//����� ��� ������� �� �������
	            }
	        });
	}

}
