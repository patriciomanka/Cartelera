<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Carrera extends CI_Controller {
	
	public function __construct () {
		parent::__construct ();
		$this->load->model ('carrera_model');
	}
	
	public function index()
	{
			$data['carreras'] = $this->carrera_model->get_carreras ();
			if (empty ($data['carreras']))
				show_404 ();
			$this->load->view ('templates/headerJSON', $data);
			$this->load->view ('carrera/index', $data);
	}
	public function getMaterias() // $carrera
	{
			$carrera=$this->input->post('carrera');
			$data['materias'] = $this->carrera_model->get_materias ($carrera);
			if (empty ($data['materias']))
				show_404 ();
			$this->load->view ('templates/headerJSON', $data);
			$this->load->view ('carrera/materias', $data);
	}
	public function getComision() //$carrera, $materia, $turno
	{
			$carrera=$this->input->post('carrera');
			$materia=$this->input->post('materia');
			$turno=$this->input->post('turno');
			$data['comisiones'] = $this->carrera_model->get_comision ($carrera, $materia, $turno);
			if (empty ($data['comisiones']))
				show_404 ();
			$this->load->view ('templates/headerJSON', $data);
			$this->load->view ('carrera/comisiones', $data);
	}
}
