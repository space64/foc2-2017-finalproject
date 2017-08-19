package gui;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import da.BrandDA;
import da.CategoryDA;
import da.ProductDA;
import da.UnitOfMeasureDA;
import dataobject.Brand;
import dataobject.Category;
import dataobject.UnitOfMeasure;

public class AddProduct extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUnitPrice;
	private JTextField txtProductCode;
	
	private ProductDA productDA;
	private CategoryDA catDA;
	private BrandDA brandDA;
	private UnitOfMeasureDA unitDA;
	
	private JButton btnAdd;
	private JButton btnCancel;
	private JComboBox<Category> cmbCategory;
	private JComboBox<UnitOfMeasure> cmbUnit;
	private JComboBox<Brand> cmbBrand;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
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
	public AddProduct() {
		productDA = new ProductDA();
		catDA = new CategoryDA();
		brandDA = new BrandDA();
		unitDA = new UnitOfMeasureDA();
		
		initGUI();
	}

	private void initGUI() {
		setResizable(false);
		setTitle("Add Product - Huyen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(25, 102, 74, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(137, 99, 173, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(25, 133, 74, 14);
		contentPane.add(lblCategory);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setBounds(25, 222, 74, 14);
		contentPane.add(lblUnitPrice);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(137, 219, 173, 20);
		contentPane.add(txtUnitPrice);
		
		JLabel lbProductCode = new JLabel("Product Code");
		lbProductCode.setBounds(25, 71, 74, 14);
		contentPane.add(lbProductCode);
		
		txtProductCode = new JTextField();
		txtProductCode.setColumns(10);
		txtProductCode.setBounds(137, 68, 173, 20);
		contentPane.add(txtProductCode);
		
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddProduct.setBounds(39, 27, 229, 14);
		contentPane.add(lblAddProduct);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(61, 272, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(161, 272, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);
		
		cmbCategory = new JComboBox<Category>();
		Vector<Category> catList = catDA.getCategories();
		cmbCategory.setModel(new DefaultComboBoxModel<Category>(catList));
		cmbCategory.setBounds(137, 130, 173, 20);
		contentPane.add(cmbCategory);
		
		JLabel lblUnitOfMeasre = new JLabel("Unit of Measure");
		lblUnitOfMeasre.setBounds(25, 164, 102, 14);
		contentPane.add(lblUnitOfMeasre);
		
		cmbUnit = new JComboBox<UnitOfMeasure>();
		Vector<UnitOfMeasure>unitList = unitDA.getUnitOfMeasure();
		cmbUnit.setModel(new DefaultComboBoxModel<UnitOfMeasure>(unitList));
		cmbUnit.setBounds(137, 161, 173, 20);
		contentPane.add(cmbUnit);
		
		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setBounds(25, 192, 74, 14);
		contentPane.add(lblBrand);
		
		cmbBrand = new JComboBox<Brand>();
		Vector<Brand> brandList = brandDA.getBrands();
		cmbBrand.setModel(new DefaultComboBoxModel<Brand>(brandList));
		cmbBrand.setBounds(137, 189, 173, 20);
		contentPane.add(cmbBrand);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			addProduct();
			productDA.getAllProducts();
		}else if(e.getSource() == btnCancel){
			AddProduct.this.dispose();
		}
		
	}

	private void addProduct() {
		String productCode = txtProductCode.getText();
		String productName = txtName.getText();
		double unitPrice = Double.parseDouble(txtUnitPrice.getText());
		Category selectedCat = (Category)cmbCategory.getSelectedItem();
		Brand selectedBrand = (Brand)cmbBrand.getSelectedItem();
		UnitOfMeasure selectedUnit = (UnitOfMeasure)cmbUnit.getSelectedItem();
		int catId = selectedCat.getCategoryId();
		int brandId = selectedBrand.getId();
		int unitId = selectedUnit.getId();
		
		productDA.insert(productCode, productName, catId, brandId, unitId, unitPrice, "");
	}
}
