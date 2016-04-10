<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Admin extends CI_Controller {
	
	public function __construct () {
		parent::__construct ();
		$this->load->model ('admin_model');
		$this->load->library(array ('session', 'form_validation'));
		$this->load->helper(array('form', 'url'));
	}
	
	public function cargarComision ()
	{
		$this->form_validation->set_rules('name', 'nombre', 'required|trim|strip_tags|htmlspecialchars|alpha_dash', array('required' => 'Debes ingresar %s.'));
		$this->form_validation->set_rules('password', 'contraseña', 'required|trim|strip_tags|htmlspecialchars|callback_verify_user', array('required' => 'Debes ingresar %s.','verify_user' => 'Contraseña incorrecta.'));
        $config['upload_path']          = './uploads/';
		$config['file_name'] 			= 'comisiones.txt';
        $config['allowed_types']        = 'txt';
		$config['overwrite']        = TRUE;
        $config['max_size']             = 1000;
		$this->load->library('upload', $config);
		
		if ((!($this->upload->do_upload())) || $this->form_validation->run()==FALSE) {
			$data['title'] = 'Login';
			$data['error'] = $this->upload->display_errors();
			$this->load->view('templates/header', $data);
			$this->load->view('admin/cargarComision');
			$this->load->view('templates/footer');
		} else {
			$nombre = $this->input->post('name');
			$this->session->set_userdata(array('name'=>$nombre,'logged'=>TRUE));
			$this->admin_model->nuevas_comisiones($this->parsearArchivo ());
			$data['title'] = 'Logged';
			$this->load->view('templates/header', $data);
			$this->load->view('admin/nuevaComision');
			$this->load->view('templates/footer');
		}
	}
	
	private function parsearArchivo () {
		$lineas = file('uploads/comisiones.txt', FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
		$comisiones = array();
		$primero = true;
		
		foreach ($lineas as $renglon) {
			$datos  = explode ("\t", $renglon);
			$comisiones[] = array("carrera"=>$this->normaliza ($datos[0]), "materia"=>$this->normaliza ($datos[1]), "turno"=>$datos[2], "comision"=>$datos[3], "horaInicio"=>$datos[4], "horaFin"=>$datos[5], "dia"=>$datos[6], "aula"=>$datos[7]);
		}
		
		return $comisiones;
	}
	
	private function normaliza ($cadena){
		$originales = 'ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞ
						ßàáâãäåæçèéêëìíîïðñòóôõöøùúûýýþÿŔŕ';
		$modificadas = 'aaaaaaaceeeeiiiidnoooooouuuuy
						bsaaaaaaaceeeeiiiidnoooooouuuyybyR ';
		$cadena = utf8_decode($cadena);
		$cadena = strtr($cadena, utf8_decode($originales), $modificadas);
		$cadena = strtolower($cadena);
		$cadena = ltrim($cadena, ' ');
		$cadena = rtrim($cadena, ' ');
		$cadena = str_replace(' ', '_', $cadena);
		return utf8_encode($cadena);
	}
	
		public function cargarUsuario () {
			$this->form_validation->set_rules('name', 'nombre', 'required|trim|strip_tags|htmlspecialchars|alpha_dash|is_unique[user.name]', array('required' => 'Debes ingresar %s.', 'is_unique'=>'%s debe ser unico'));
			$this->form_validation->set_rules('password', 'contraseña', 'required|trim|strip_tags|htmlspecialchars', array('required' => 'Debes ingresar %s.'));
			$this->form_validation->set_rules('password2', 'contraseña2', 'required|trim|strip_tags|htmlspecialchars|matches[password]', array('required' => 'Debes ingresar %s.','matches'=>'Las contraseñas no coinciden.'));

			if ($this->form_validation->run()==FALSE) {
				$data['title'] = 'Crear usuario';
				$this->load->view('templates/header', $data);
				$this->load->view('admin/cargarUsuario');
				$this->load->view('templates/footer');
			} else {
				$this->admin_model->new_user();
				$data['title'] = 'Nuevo usuario';
				$this->load->view('templates/header', $data);
				$this->load->view('admin/nuevoUsuario');
				$this->load->view('templates/footer');
			}
		}
	
	public function verify_user () {
		return $this->admin_model->verify_user ();
	}
}
