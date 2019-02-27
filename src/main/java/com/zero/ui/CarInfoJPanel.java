package com.zero.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;

import com.zero.domain.CarInfoVO;

import javax.swing.event.ChangeEvent;

public class CarInfoJPanel extends JPanel {
	private JTextField text_car_name;
	private JTextField text_car_number;
	private JTextField text_car_owner;
	private JSpinner spinner_insurance_date_y;
	private JSpinner spinner_insurance_date_m;
	private JSpinner spinner_insurance_date_d;
	private Date insurance_date;
	private JLabel label_insurance_card_val;
	private JLabel label_car_card_val;
	private JLabel label_relationship_carowner_val;
	private JCheckBox chckbx_insurance_boonean;

	/**
	 * Create the panel.
	 */
	public CarInfoJPanel() {
		setBorder(new TitledBorder(null, "car_info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel label_insurance_card = new JLabel("\uBCF4\uD5D8\uC99D : ");
		panel.add(label_insurance_card);
		
		label_insurance_card_val = new JLabel("");
		panel.add(label_insurance_card_val);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JLabel label_car_card = new JLabel("\uCC28\uB7C9 \uB4F1\uB85D\uC99D : ");
		panel_2.add(label_car_card);
		
		label_car_card_val = new JLabel("");
		panel_2.add(label_car_card_val);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JLabel label_relationship_carowner = new JLabel("\uCC28\uB7C9\uC18C\uC720 \uAD00\uACC4 : ");
		panel_3.add(label_relationship_carowner);
		
		label_relationship_carowner_val = new JLabel("");
		panel_3.add(label_relationship_carowner_val);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		
		JLabel label_car_name = new JLabel("\uCC28\uC885 : ");
		panel_4.add(label_car_name);
		
		text_car_name = new JTextField();
		text_car_name.setColumns(10);
		panel_4.add(text_car_name);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		
		JLabel label_car_number = new JLabel("\uCC28\uB7C9 \uBC88\uD638 :");
		panel_5.add(label_car_number);
		
		text_car_number = new JTextField();
		text_car_number.setColumns(10);
		panel_5.add(text_car_number);
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		
		JLabel label_insurance_date = new JLabel("\uBCF4\uD5D8\uB9CC\uB8CC\uC77C : ");
		panel_6.add(label_insurance_date);
		
		insurance_date = new Date(1551249370828L);
		
		setSpinner();
		
		panel_6.add(spinner_insurance_date_m);
		panel_6.add(spinner_insurance_date_y);
		panel_6.add(spinner_insurance_date_d);
		
		JPanel panel_7 = new JPanel();
		add(panel_7);
		
		JLabel label_insurance_boonean = new JLabel("\uBCF4\uD5D8\uB2F4\uBCF4 : ");
		panel_7.add(label_insurance_boonean);
		
		chckbx_insurance_boonean = new JCheckBox("\uBCF4\uD5D8 \uC801\uC6A9\uB428");
		panel_7.add(chckbx_insurance_boonean);
		
		JPanel panel_8 = new JPanel();
		add(panel_8);
		
		JLabel label_car_owner = new JLabel("\uCC28\uB7C9\uC18C\uC720\uC8FC\uC774\uB984 : ");
		panel_8.add(label_car_owner);
		
		text_car_owner = new JTextField();
		text_car_owner.setColumns(10);
		panel_8.add(text_car_owner);

	}

	private void setSpinner() {
		spinner_insurance_date_y = new JSpinner();		
		spinner_insurance_date_y.setModel(new SpinnerDateModel(insurance_date, null, null, Calendar.DAY_OF_MONTH));
		spinner_insurance_date_y.setEditor(new JSpinner.DateEditor(spinner_insurance_date_y, "yyyy"));
		
		
		
		spinner_insurance_date_m = new JSpinner();
		
		spinner_insurance_date_m.setModel(new SpinnerDateModel(insurance_date, null, null, Calendar.DAY_OF_MONTH));
		spinner_insurance_date_m.setEditor(new JSpinner.DateEditor(spinner_insurance_date_m, "MM"));
		
		
		spinner_insurance_date_d = new JSpinner();
		
		spinner_insurance_date_d.setModel(new SpinnerDateModel(insurance_date, null, null, Calendar.DAY_OF_MONTH));
		spinner_insurance_date_d.setEditor(new JSpinner.DateEditor(spinner_insurance_date_d, "dd"));
	}

	public void setLadel(CarInfoVO carInfoVO) {
		label_car_card_val.setText(carInfoVO.getCarCard());
		label_insurance_card_val.setText(carInfoVO.getInsuranceCard());
		label_relationship_carowner_val.setText(carInfoVO.getRelationshipCarowner());
		
	}

	public CarInfoVO getCarInfor() {
		if ((text_car_name.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "차종을 입력해주세요");
			return null;
		}
		if ((text_car_number.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "차량 번호를 입력해주세요");
			return null;
		}
		if (!chckbx_insurance_boonean.isSelected()) {
			JOptionPane.showMessageDialog(null, "보험이 적용되어있지않으면 이용불가능");
			return null;
		}
		if ((text_car_owner.getText().trim()).equals("")) {
			JOptionPane.showMessageDialog(null, "차량 소유주이름을 입력해주세요");
			return null;
		}
		
		CarInfoVO vo = new CarInfoVO();
		vo.setCarName(text_car_name.getText());
		vo.setCarNumber(text_car_number.getText());
		
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		String year = yy.format((Date) spinner_insurance_date_y.getValue());
		
		SimpleDateFormat MM = new SimpleDateFormat("MM");
		String month = MM.format((Date) spinner_insurance_date_m.getValue());
		
		SimpleDateFormat dd = new SimpleDateFormat("dd");
		String day = dd.format((Date) spinner_insurance_date_d.getValue());
		
		String date = year+"-"+month+"-"+day;
		Date InsuranceDate = null;
		try {
			InsuranceDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setInsuranceDate(InsuranceDate);
		vo.setInsuranceBoonean(chckbx_insurance_boonean.isSelected());
		vo.setCarOwner(text_car_owner.getText());
		

		return vo;
	}

	public void setNull() {
		label_car_card_val.setText("");
		label_insurance_card_val.setText("");
		label_relationship_carowner_val.setText("");
		text_car_name.setText("");
		text_car_number.setText("");
		text_car_owner.setText("");
		chckbx_insurance_boonean.setSelected(false);
		setSpinner();
	}

	
	
	

}
