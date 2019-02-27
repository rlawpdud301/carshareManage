package com.zero.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.border.TitledBorder;

import com.zero.domain.LicenseInfoVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class LicenseInfoJPanel extends JPanel {
	private JTextField text_license_no;
	private JTextField text_license_kind;
	private JTextField text_certification_no;
	private JLabel label_license_photo_val;
	private JSpinner spinner_birthday_m;
	private JSpinner spinner_birthday_y;
	private JSpinner spinner_birthday_d;
	private JSpinner spinner_deadline_y;
	private JSpinner spinner_deadline_m;
	private JSpinner spinner_deadline_d;

	/**
	 * Create the panel.
	 */
	public LicenseInfoJPanel() {
		setBorder(new TitledBorder(null, "license_Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel label_license_photo = new JLabel("\uBA74\uD5C8 \uC0AC\uC9C4 : ");
		panel_1.add(label_license_photo);
		
		label_license_photo_val = new JLabel("");
		panel_1.add(label_license_photo_val);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JLabel label_license_no = new JLabel("\uBA74\uD5C8 \uBC88\uD638 : ");
		panel_2.add(label_license_no);
		
		text_license_no = new JTextField();
		text_license_no.setColumns(10);
		panel_2.add(text_license_no);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JLabel label_license_kind = new JLabel("\uBA74\uD5C8 \uC885\uB958 : ");
		panel_3.add(label_license_kind);
		
		text_license_kind = new JTextField();
		text_license_kind.setColumns(10);
		panel_3.add(text_license_kind);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		
		JLabel label_birthday = new JLabel("\uC0DD\uB144\uC6D4\uC77C : ");
		panel_4.add(label_birthday);
		
		setSpinner();
		
		
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		
		JLabel label_deadline = new JLabel("\uBA74\uD5C8\uAC31\uC2E0 \uB9C8\uAC10\uC77C : ");
		panel_5.add(label_deadline);
		
		
		panel_4.add(spinner_birthday_y);
		panel_4.add(spinner_birthday_m);
		panel_4.add(spinner_birthday_d);
		
		panel_5.add(spinner_deadline_y);
		panel_5.add(spinner_deadline_m);
		panel_5.add(spinner_deadline_d);
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		
		JLabel label_certification_no = new JLabel("\uBA74\uD5C8\uC778\uC99D\uBC88\uD638 : ");
		panel_6.add(label_certification_no);
		
		text_certification_no = new JTextField();
		text_certification_no.setColumns(10);
		panel_6.add(text_certification_no);

	}

	private void setSpinner() {
		spinner_birthday_y = new JSpinner();
		spinner_birthday_y.setModel(new SpinnerDateModel(new Date(1551262153520L), null, new Date(1551262153520L), Calendar.DAY_OF_MONTH));
		spinner_birthday_y.setEditor(new JSpinner.DateEditor(spinner_birthday_y, "yyyy"));
		
		
		spinner_birthday_m = new JSpinner();
		spinner_birthday_m.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinner_birthday_m.setEditor(new JSpinner.DateEditor(spinner_birthday_m, "MM"));
		
		
		spinner_birthday_d = new JSpinner();
		spinner_birthday_d.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinner_birthday_d.setEditor(new JSpinner.DateEditor(spinner_birthday_d, "dd"));
		
		spinner_deadline_y = new JSpinner();
		spinner_deadline_y.setModel(new SpinnerDateModel(new Date(1551262162323L), null, null, Calendar.DAY_OF_MONTH));
		spinner_deadline_y.setEditor(new JSpinner.DateEditor(spinner_deadline_y, "yyyy"));
		
		
		spinner_deadline_m = new JSpinner();
		spinner_deadline_m.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinner_deadline_m.setEditor(new JSpinner.DateEditor(spinner_deadline_m, "MM"));
		
		
		spinner_deadline_d = new JSpinner();
		spinner_deadline_d.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
		spinner_deadline_d.setEditor(new JSpinner.DateEditor(spinner_deadline_d, "dd"));
	}

	public void setLadel(LicenseInfoVO licenseInfoVO) {
		label_license_photo_val.setText(licenseInfoVO.getLicensePhoto());
	}

	public LicenseInfoVO getLicenseInfo() {
		if ((text_license_no.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "면허 번호를 입력해주세요");
			return null;
		}
		if ((text_license_kind.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "면허 종류를 입력해주세요");
			return null;
		}
		if ((text_certification_no.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "면허인증번호를 입력해주세요");
			return null;
		}
		
		
		LicenseInfoVO vo = new LicenseInfoVO();
		vo.setLicenseNo(text_license_no.getText());
		vo.setLicenseKind(text_license_kind.getText());
		vo = getDate(vo);
		vo.setCertificationNo(text_certification_no.getText());
		return vo;
	}

	private LicenseInfoVO getDate(LicenseInfoVO vo) {
		
		
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		String year = yy.format((Date) spinner_birthday_y.getValue());
		
		SimpleDateFormat MM = new SimpleDateFormat("MM");
		String month = MM.format((Date) spinner_birthday_m.getValue());
		
		SimpleDateFormat dd = new SimpleDateFormat("dd");
		String day = dd.format((Date) spinner_birthday_d.getValue());
		
		String date = year+"-"+month+"-"+day;
		Date birthday = null;
		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setBirthday(birthday);
		
		year = yy.format((Date) spinner_deadline_y.getValue());
		month = MM.format((Date) spinner_deadline_m.getValue());
		day = dd.format((Date) spinner_deadline_d.getValue());
		
		date = year+"-"+month+"-"+day;
		Date deadline = null;
		try {
			deadline = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setDeadline(deadline);
		return vo;
	}

	public void setNull() {
		label_license_photo_val.setText("");
		text_certification_no.setText("");
		text_license_kind.setText("");
		text_license_no.setText("");
		setSpinner();
	}

}
