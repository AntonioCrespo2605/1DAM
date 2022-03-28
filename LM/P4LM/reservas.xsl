<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:strip-space elements="*" />
	
	<xsl:template match="reservas">
		<?xml-stylesheet href="reservas.css" type="text/css"?>
		<html>
		<table border="1" cellspacing="0">
			<tr style="background-color:#DDD">
				<th colspan="3">Reserva de salones</th>
			</tr>
			<tr style="background-color:#DDD" align="center">
				<td>Fecha entrada</td>
				<td>Cliente</td>
				<td>Observaciones</td>
			</tr>

			<xsl:for-each select="reserva_salón">
				<xsl:sort data-type="number" select="((fechaEntrada/@mes='Enero')*1)+((fechaEntrada/@mes='Febrero')*2)+((fechaEntrada/@mes='Marzo')*3)+((fechaEntrada/@mes='Abril')*4)+((fechaEntrada/@mes='Mayo')*5)+((fechaEntrada/@mes='Junio')*6)+((fechaEntrada/@mes='Julio')*7)+((fechaEntrada/@mes='Agosto')*8)+((fechaEntrada/@mes='Septiembre')*9)+((fechaEntrada/@mes='Octubre')*10)+((fechaEntrada/@mes='Noviembre')*11)+((fechaEntrada/@mes='Diciembre')*12)"/>
				<tr>
					<td><xsl:value-of select="fechaEntrada/@día" />-<xsl:value-of select="fechaEntrada/@mes" />-<xsl:value-of select="fechaEntrada/@año" /></td>
					<td><xsl:value-of select="@cliente" /></td>
					<td><xsl:value-of select="observaciones" /></td>
				</tr>
			</xsl:for-each>
		</table>
		</html>
	</xsl:template>
</xsl:stylesheet>