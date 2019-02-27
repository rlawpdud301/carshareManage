package com.zero.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.zero.domain.MemberVO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ApplyMemberJPanel extends JPanel {
	
	private JTable table;
	private NonEditableModel model;
	private List<MemberVO> list;

	/**
	 * Create the panel.
	 */
	public ApplyMemberJPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		
		
		scrollPane.setViewportView(table);

	}
	public void setList(List<MemberVO> list) {
		this.list = list;
		loadDatas();
	}
	
	public void loadDatas() {
		model = new NonEditableModel(getDatas(), getColumnNames());
		table.setModel(model);
		/*setAlignWith();*/
	}

	public Object[][] getDatas() {
		Object[][] datas = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = getDriverApplyArray(list.get(i));
		}
		return datas;
		
	}

	public String[] getColumnNames() {
		return new String[] { "회원번호", "이름", "신청일시"};
	}
	
	private Object[] getDriverApplyArray(MemberVO memberVO) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		
		return new Object[] {memberVO.getMemberNo(),memberVO.getName(),date.format(memberVO.getDirverApply())};
	}
	
	public JTable getTable() {
		return table;

	}
	
	public MemberVO selectedItem() {
		int selectedIndex = table.getSelectedRow();
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요");
			return null;
		}
		return list.get(selectedIndex);
	}

}
