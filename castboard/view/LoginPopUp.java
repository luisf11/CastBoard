package castboard.view;

import castboard.domain.CatalogsHandler;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;

public class LoginPopUp extends PopUp
{
	private JPanel pnlForm;
	private JTextField txtAgent;
	private JPasswordField txtPass;
	
	public LoginPopUp(JFrame frmParent)
	{
		super(frmParent, "Inicio de sesión - CastBoard", JOptionPane.OK_CANCEL_OPTION, 
			  (new ImageIcon("castboard/res/icons/ico_64_cb.png")));
		
		createPnlForm();
	}
	
	public void display ()
	{
			super.display(pnlForm);
	}

	public void optionSelected (int option)
	{
		if (option == JOptionPane.OK_OPTION)
		{
			String user = txtAgent.getText();
			String pass = new String(txtPass.getPassword());
			
			if (user.equals("") || pass.equals(""))
			{
				(new CautionPopUp(frmParent)).display("'Agente' y 'Contraseña' deben ser ingresados");
				display();
			}
			else if (CatalogsHandler.connect(user, pass))
				((MasterFrame) frmParent).logIn();
			else
			{
				(new FailureNotificationPopUp(frmParent)).display("Lo sentimos, no se ha podido " +
																  "establecer conexion.\nRevise su" +
																  " 'Agente' y 'Contraseña', si el " +
																  "problema\npersiste contacte al DBA");
				display();
			}
		}
		else if (option == JOptionPane.CANCEL_OPTION)
		{
			(new CautionPopUp(frmParent)).display("'Agente' y 'Contraseña' deben ser ingresados");
			display();
		}
	}

	private void createPnlForm ()
	{
		JLabel lblAgent = new JLabel("Agente");
		JLabel lblPass = new JLabel("Contraseña");
		
		txtAgent = new JTextField(16);
		txtPass = new JPasswordField(16);
		
		JPanel pnlAgent = new JPanel();
		JPanel pnlPass = new JPanel();
		
		pnlForm = new JPanel();
		pnlForm.setLayout(new BorderLayout());
		
		pnlAgent.add(lblAgent);
		pnlAgent.add(txtAgent);
		pnlPass.add(lblPass);
		pnlPass.add(txtPass);
		
		pnlForm.add(pnlAgent, BorderLayout.NORTH);
		pnlForm.add(pnlPass, BorderLayout.SOUTH);
	}
}