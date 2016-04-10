<h2><?php echo "Cargar nuevas comisiones" ?></h2>
<?php
	echo validation_errors();
	echo form_open_multipart ('admin/cargarComision');
?>
	<label for="name">Nombre</label>
	<input type="text" name="name"/><br/>
	<label for="password">Password</label>
	<input type="password" name="password"/><br/>
	<label for="file">Seleccione el archivo con la información de las comisiones: </label>
	<input type="file" name="userfile" size="20" >

	<input type="submit" name="submit" value="cargar" />

</form>

<?php
	echo $error;
?>
