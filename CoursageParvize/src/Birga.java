import javax.swing.*;								//������ � ������ �������
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumn;

class Birga extends Frame {
// ���������� ����������� ������ ����������

    final DefaultListModel modellistJob = new DefaultListModel();
    private TextField name, dolshnost, trebovania, uslovia, oplata, contacts; // ���� �����
    private JList listJob;		//������
    private JList listArc;
    private JList listFirmW;
    // ������
    private JScrollPane listScrollPaneF;
    private JScrollPane listScrollPaneJob;
    private JScrollPane listScrollPaneArc;
    private JScrollPane testTablePane;
    private javax.swing.JTable jTable;		//�������
    private ArrayList anketa = new ArrayList();// ������ ����
    private ArrayList archive = new ArrayList(); //����� ������
    private ArrayList vacancy = new ArrayList(); //����� ������
    // �������� �������
    private Object[] headers = {"�����", "���������", "����������", "�������", "��", "��������"};	//�������

    private Object[][] data = {					//��������� ������ � �������
        {null, null, null, null, null, null}
 
    };

// ����������
    Birga(String s) {
        super(s);
        setLayout(null);
        Font f = new Font("Serif", Font.BOLD, 18);		//����� , �����, ������ 18 ��
        setFont(f); //���������
        // ���������� �������� �� ������

        Label l1 = new Label("�������� �����:", Label.RIGHT);		//��������� ���� 
        l1.setBounds(0, 45, 180, 18);								// ���������� ��������� (X,Y,������, ������)						
        add(l1);													//��������� � ����
        Label l2 = new Label("���������:", Label.RIGHT);
        l2.setBounds(0, 80, 180, 18);
        add(l2);
        Label l3 = new Label("���������� :", Label.RIGHT);
        l3.setBounds(0, 115, 180, 18);
        add(l3);
        Label l4 = new Label("������� �����:", Label.RIGHT);
        l4.setBounds(0, 150, 180, 18);
        add(l4);
        Label l5 = new Label("�/�:", Label.RIGHT);
        l5.setBounds(0, 185, 180, 18);
        add(l5);
        Label l6 = new Label("���������� ������:", Label.RIGHT);
        l6.setBounds(0, 215, 180, 18);
        add(l6);
        Label l7 = new Label("��������:", Label.RIGHT);
        l7.setBounds(0, 355, 140, 18);
        add(l7);
        Label l8 = new Label("�����:", Label.RIGHT);
        l8.setBounds(330, 355, 140, 18);
        add(l8);

        // ���������� ��������� ��������
        name = new TextField(30);			//�������� ���� �������� 30 
        name.setBounds(200, 42, 300, 25);	//�������������
        add(name);							//���������
        dolshnost = new TextField(30);
        dolshnost.setBounds(200, 76, 300, 25);
        add(dolshnost);
        trebovania = new TextField(45);
        trebovania.setBounds(200, 110, 300, 25);
        add(trebovania);
        uslovia = new TextField(45);
        uslovia.setBounds(200, 145, 300, 25);
        add(uslovia);
        oplata = new TextField(45);
        oplata.setBounds(200, 180, 300, 25);
        add(oplata);
        contacts = new TextField(45);
        contacts.setBounds(200, 215, 300, 25);
        add(contacts);

        // ������ ����
        Button dob = new Button("��������");	//������
        dob.setBounds(110, 250, 100, 30);		//�������
        add(dob);// ��������� ������ �� ������
        dob.addActionListener(new Dob());
        Button search = new Button("�����");
        search.setBounds(220, 250, 100, 30);
        add(search);
        search.addActionListener(new poisk());
        Button delete1 = new Button("������� �� ������");
        delete1.setBounds(430, 590, 200, 30);
        add(delete1);
        delete1.addActionListener(new deleteFromArchive());
        Button delete2 = new Button("������� �� ����");
        delete2.setBounds(560, 300, 200, 30);
        add(delete2);
        delete2.addActionListener(new deleteFromFirms());
        Button v = new Button("�����");
        v.setBounds(770, 740, 100, 30);
        add(v);
        v.addActionListener(new ActionListener() {				//������ ������, ��������� ���������� �������
            public void actionPerformed(ActionEvent e) {
                System.exit(0);									// �����
            }
        });
        Button arch = new Button("�������� � �����");
        arch.setBounds(50, 590, 210, 30);
        add(arch);
        arch.addActionListener(new ToArchive());				//���������� ������� ������ ���
        // ���������� � ������� �������� �������
        jTable = new JTable(data, headers);
        testTablePane = new JScrollPane(jTable);
        testTablePane.setBounds(20, 660, 900, 50);
        add(testTablePane);

        // �������� � ���������� �������
        listFirmW = new JList();
        listScrollPaneF = new JScrollPane(listFirmW);
        listScrollPaneF.setBounds(550, 45, 300, 200);
        listFirmW.addListSelectionListener(new JobSelection());
        add(listScrollPaneF);

        listJob = new JList(modellistJob);
        listScrollPaneJob = new JScrollPane(listJob);
        listScrollPaneJob.setBounds(10, 375, 300, 200);
        add(listScrollPaneJob);
        listArc = new JList();
        listScrollPaneArc = new JScrollPane(listArc);
        listScrollPaneArc.setBounds(400, 375, 300, 200);
        add(listScrollPaneArc);

        setBackground(Color.WHITE);							//���
        //������//������ ����
        setSize(930, 790);
        setVisible(true); //������ ����� �������
        jTable.setRowHeight(30);
        //���� ��� ��������� �������� �������� �������
        for (int i = 0; i < 6; i++) {
            TableColumn col = jTable.getColumnModel().getColumn(i);	
            switch (i) {
                case 0:
                    col.setPreferredWidth(100);				//������ �������
                    break;
                case 1:
                    col.setPreferredWidth(90);
                    break;
                case 2:
                    col.setPreferredWidth(140);
                    break;
                case 3:
                    col.setPreferredWidth(100);
                    break;
                case 4:
                    col.setPreferredWidth(50);
                    break;
                case 5:
                    col.setPreferredWidth(50);
                    break;
                default:
                    col.setPreferredWidth(100);
                    break;
            }
        }
        addFirms();									//��������� �����
    }

    // ��������� ������
    void addFirms() {
        anketa.add(new Firma("OOO ������� �������", "��������", "�������� �����������", "������ ������", "5500", "+380-093-456-89-67"));
        anketa.add(new Firma("������� ������", "��������", "", "������ ������� ����", "3000", "+380-247-925-84"));
        anketa.add(new Firma("��� �������", "��������", "���� ������", "�������� ������� ����", "4500", "+380-096-56-09"));
        anketa.add(new Firma("������� �������", "��������", "��������", "������ �� ��������", "2000", "+380-050-876-82"));
        refreshLists();
    }

    // ����� ������� �����
    private void clearFields() {
        name.setText("");	
        dolshnost.setText("");
        trebovania.setText("");
        uslovia.setText("");
        oplata.setText("");
        contacts.setText("");
    }

    // ����� ���������� �������
    private void refreshLists() {
        String[] toFirms = new String[anketa.size()];
        String[] toArchive = new String[archive.size()];
        Firma f;
        //����������, ��� ��� � �������� ��
        for (int i = 0; i < anketa.size(); i++) {
            if (anketa.get(i) != null && !((Firma) anketa.get(i)).isInArchive()) {
                f = (Firma) anketa.get(i);
                toFirms[i] = f.getName();
            } else {
                continue;
            }
        }
        //����������, ��� ��� � �����
        for (int i = 0; i < archive.size(); i++) {
            if (archive.get(i) != null && ((Firma) archive.get(i)).isInArchive()) {
                f = (Firma) archive.get(i);
                toArchive[i] = f.getName();
            } else {
                continue;
            }
        }
        // ����������� �������
        listFirmW.setListData(toFirms);
        if (vacancy.size() > 0) {
            listJob.setListData(vacancy.toArray());
        } else {
            listJob.setListData(new String[0]);
        }
        listArc.setListData(toArchive);
    }
    // ��������� �������
    // ����� �� ������

    class JobSelection implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent listSelectionEvent) {	
            //���������� ��������������� ������� �������);
            String nf = (String) listFirmW.getSelectedValue();
            for (Object firma : anketa) {						//������ � �������
                if (firma != null && ((Firma) firma).getName().equals(nf)) {
                    jTable.setValueAt(((Firma) firma).getName(), 0, 0);			//������
                    jTable.setValueAt(((Firma) firma).getDolshnost(), 0, 1);	//������
                    	
                    jTable.setValueAt(((Firma) firma).getTrebovania(), 0, 2);
                    jTable.setValueAt(((Firma) firma).getUslovia(), 0, 3);
                    jTable.setValueAt(((Firma) firma).getOplata(), 0, 4);
                    jTable.setValueAt(((Firma) firma).getContacts(), 0, 5);
                } else {
                }
            }
        }
    }

    // ��������
    class Close implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            System.exit(0);		//���������
        }
    }

    //�������� �� ������
    class deleteFromArchive implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            String selectedArc = listArc.getSelectedValue().toString();
            for (int i = 0; i < archive.size(); i++) {
                Firma f = (Firma) archive.get(i);
                if (selectedArc.equals(f.getName())) {
                    archive.remove(i);			//�������
                    f.setArc(false);
                    anketa.add(f);
                    break;
                }
            }
            refreshLists();						//��������� ������
        }
    }

    // �������� �� ����
    class deleteFromFirms implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            String selectedAnketa = listFirmW.getSelectedValue().toString();
            for (int i = 0; i < anketa.size(); i++) {
                Firma f = (Firma) anketa.get(i);
                if (selectedAnketa.equals(f.getName())) {
                    anketa.remove(i);				//�������
                    break;
                }
            }
            refreshLists();							//���������
        }
    }

    //���������� �����
    class Dob implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(new JFrame(),		//����������� ���� ��� �������������
                        "��� ������������ �����", "������", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (dolshnost.equals("")) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "��� ������������ ���������", "������", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (oplata.equals("")) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "�� ������� ��������", "������", JOptionPane.WARNING_MESSAGE);
                return;
            }
            anketa.add(new Firma(name.getText(), dolshnost.getText(),
                    trebovania.getText(), uslovia.getText(), oplata.getText(),
                    contacts.getText()));
            refreshLists();
            clearFields();
        }
    }
//  ���������� �������� � �����  

    class ToArchive implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if (listJob.getSelectedIndices().length == 0) {
                return;// ������ �� �������
            }
            String selectedVac = listJob.getSelectedValue().toString();		//����� ��������� ��������
            int iFound = -1;					
            for (int i = 0; i < anketa.size(); i++) {
                Firma f = (Firma) anketa.get(i);
                if (selectedVac.equals(f.getName())) {
                    iFound = i;
                    break;
                }
            }
            if (iFound == -1) {
                return;// �� �������
            }
            for (int i = 0; i < vacancy.size(); i++) {
                if (selectedVac.equals(vacancy.get(i))) {
                    vacancy.remove(i);						//������� �� ������ 
                    break;
                }
            }
            Firma f = (Firma) anketa.get(iFound);
            anketa.remove(iFound);
            f.setArc(true);
            archive.add(f);									//� ��������� � �����
            refreshLists();									//���������
        }
    }

    // ����� ����������� ������

    class poisk implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            int izp = 0;
            String zp = oplata.getText();// ����, ��� ������c� ��
            if (!zp.equals("")) {
                izp = Integer.parseInt(zp);// ������� � �������� ��� ��� �� ��������
            }
            String dol = dolshnost.getText();

            if (dol.equals("") && zp.equals("")) {
                JOptionPane.showMessageDialog(new JFrame(),			//����������� ����
                        "��� ���������� ������", "������", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List found = new ArrayList();							
            														
            for (int i = 0; i < anketa.size(); i++) {				
                Firma f = (Firma) anketa.get(i);					
                if ((dol.equals(f.getDolshnost()) || dol.equals("")) && (izp <= f.getOplata() || izp == 0)) {
                    found.add(f.name);				//��������� � ������ ���� �������� ��� �����
                }
            }
            vacancy.clear();			//�������
            vacancy.addAll(found);		
            listJob.setListData(found.toArray());		
            clearFields();								
        }
    }

    class Firma {

        int k; // �������
        String name, dolshnost, trebovania, uslovia, oplata, contacts;

        Boolean arc;

        Firma(String name, String dolshnost, String trebovania, String uslovia,		//����������� �����
                String oplata, String contacts) {
            this.name = name;														//��������� ������
            this.dolshnost = dolshnost;
            this.trebovania = trebovania;
            this.uslovia = uslovia;
            this.oplata = oplata;
            this.contacts = contacts;

            this.arc = false;
            k++;
        }

        public void setArc(Boolean arc) {					//�����
            this.arc = arc;
        }

        public Boolean isInArchive() {						//���� �� � �����?
            return arc;
        }

        public String getName() {							//������ ���
            return name;
        }

        public void setName(String name) {					//�������������� ���
            this.name = name;
        }

        public String getDolshnost() {						//��� �����
            return dolshnost;
        }

        public void setDolshnost(String dolshnost) {		//��������
            this.dolshnost = dolshnost;
        }

        public String getTrebovania() {						//�������� ������
            return trebovania;
        }

        public String getUslovia() {						//��� �����
            return uslovia;
        }

        public void setUslovia(String uslovia) {			//��� �����
            this.uslovia = uslovia;
        }

        public String getContacts() {						//��� ��������
            return contacts;
        }

        public void setContacts(String contacts) {			//����� �������
        }

        public int getOplata() {							//������ ���
            if (oplata.equals("")) {
                return 0;
            } else {
                return Integer.parseInt(oplata);
            }
        }

        public void setOplata(String oplata) {				//���� �����
            this.oplata = oplata;
        }

        public void setTrebovania(String trebovania) {		//����� ������
            this.trebovania = trebovania;
        }

    }
}
