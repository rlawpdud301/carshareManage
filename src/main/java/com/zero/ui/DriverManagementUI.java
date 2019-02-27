package com.zero.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.zero.domain.CarInfoVO;
import com.zero.domain.LicenseInfoVO;
import com.zero.domain.MemberVO;
import com.zero.service.MemberService;
import com.zero.service.MemberServiceImpl;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BoxLayout;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DriverManagementUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private List<MemberVO> list;

	private MemberService service;
	private JLabel lbl_memberNo;
	private JLabel lbl_name;
	private ApplyMemberJPanel applyMemberJPanel;
	private CarInfoJPanel carInfoJPanel;
	private LicenseInfoJPanel licenseInfoJPanel;
	private MemberVO memberVO;
	private JButton btn_no;
	private JButton btn_ok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverManagementUI frame = new DriverManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DriverManagementUI() {
		service = new MemberServiceImpl();
		list = service.selectMemberApply();
		initComponents();
	}

	private void initComponents() {
		setTitle("\uC6B4\uC804\uC790 \uAD00\uB9AC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		applyMemberJPanel = new ApplyMemberJPanel();
		applyMemberJPanel.setBorder(new TitledBorder(null, "\uC2E0\uCCAD\uC790 \uBA85\uB2E8", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane.add(applyMemberJPanel, BorderLayout.CENTER);

		applyMemberJPanel.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				memberVO = applyMemberJPanel.selectedItem();
				lbl_memberNo.setText("회원 번호 : " + memberVO.getMemberNo());
				lbl_name.setText("이름 : " + memberVO.getName());

				Map<String, Object> map = service.DriverApplyInfo(memberVO.getMemberNo());
				carInfoJPanel.setLadel((CarInfoVO) map.get("CarInfoVO"));
				licenseInfoJPanel.setLadel((LicenseInfoVO) map.get("LicenseInfoVO"));

			}
		});

		applyMemberJPanel.setList(list);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\uD68C\uC6D0 \uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		carInfoJPanel = new CarInfoJPanel();
		panel_1.add(carInfoJPanel);

		licenseInfoJPanel = new LicenseInfoJPanel();
		panel_1.add(licenseInfoJPanel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);

		lbl_memberNo = new JLabel("");
		panel_2.add(lbl_memberNo);

		lbl_name = new JLabel("");
		panel_2.add(lbl_name);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);

		btn_ok = new JButton("\uC2B9\uC778");
		btn_ok.addActionListener(this);
		panel_3.add(btn_ok);

		btn_no = new JButton("\uAC70\uC808");
		btn_no.addActionListener(this);
		panel_3.add(btn_no);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn_ok) {
			do_btn_ok_actionPerformed(e);
		}
		if (e.getSource() == btn_no) {
			do_btn_no_actionPerformed(e);
		}
	}
	
	private void do_btn_no_actionPerformed(ActionEvent e) {
		if ((lbl_memberNo.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "거절하실 사용자를 선택해주세요");
			return;
		}
		String ans = (String) JOptionPane.showInputDialog(null, "이용에게 거절사유를 알려주세요.", "Input Dialog",JOptionPane.PLAIN_MESSAGE,null,null,null);
		if (ans==null) {
			JOptionPane.showMessageDialog(null, "거절이 취소되었습니다.");
			return;
		}
		
		
        String body = "죄송합니다. 다음과같은이유로 승인이힘듭니다." +ans;       // 메일 내용

        sendFromGMail(body);
		
	}

	private void do_btn_ok_actionPerformed(ActionEvent e) {
		
			
			if ((lbl_memberNo.getText().trim()).equals("")) {
				JOptionPane.showMessageDialog(null, "입력하실 사용자를 선택해주세요");
				return;
			}
			CarInfoVO carInfoVO = carInfoJPanel.getCarInfor();
			LicenseInfoVO licenseInfoVO = licenseInfoJPanel.getLicenseInfo();
			if (carInfoVO == null || licenseInfoVO == null) {
				return;
			}

			carInfoVO.setMemberNo(memberVO);
			licenseInfoVO.setMemberNo(memberVO);
			service.driverInfo(carInfoVO, licenseInfoVO);
			list = service.selectMemberApply();
			applyMemberJPanel.setList(list);
			JOptionPane.showMessageDialog(null, "등록 완료");
			lbl_memberNo.setText("");
			lbl_name.setText("");
			carInfoJPanel.setNull();
			licenseInfoJPanel.setNull();
			String body = "승인이 완료되었습니다. 기다려주셔서 감사합니다. 지금부터 드라이버로활동이 가능합니다. 즐겁고 안전한 드라이브되세요";       // 메일 내용
	        sendFromGMail(body);
		
		
	}

	private void sendFromGMail(String body) {
		String from = "rlawpdud301";
        String passWd = "dnlqh1220";
        String[] to = { memberVO.getEmail() }; // 보낼 메일 목록
        JOptionPane.showMessageDialog(null, to);
        String subject = "안녕하세요 GET입니다." ;  // 메일 제목
		
		Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", passWd);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, passWd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
        	JOptionPane.showMessageDialog(null, "실패");
            ae.printStackTrace();
        }
        catch (MessagingException me) {
        	JOptionPane.showMessageDialog(null, "실패");
            me.printStackTrace();
        }finally {
        	JOptionPane.showMessageDialog(null, "성공");
        }
    }
}
