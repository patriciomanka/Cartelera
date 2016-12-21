<h2><?php echo $title ?></h2>
<?php
	echo validation_errors();
	echo form_open ('admin/cargarUsuario');
?>
	<label for="name">Nombre</label>
	<input type="text" name="name"/><br/>
	<label for="password">Password</label>
	<input type="password" name="password"/><br/>
	<label for="password2">Confirmar Password</label>
	<input type="password" name="password2"/><br/>

	<input type="submit" name="submit" value="Crear nuevo usuario" />

</form>
