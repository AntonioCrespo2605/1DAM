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
			<xsl:apply-templates/>
		</table>
		</html>
	</xsl:template>

	<xsl:template match="reserva_salón">
		<tr>
			<td><xsl:value-of select="fechaEntrada/@día" />-<xsl:value-of select="fechaEntrada/@mes" />-<xsl:value-of select="fechaEntrada/@año" /></td>
			<td><xsl:value-of select="@cliente" /></td>
			<td><xsl:value-of select="observaciones" /></td>
		</tr>
	</xsl:template>

	<xsl:template match="reserva_habitación">

	</xsl:template>
	<xsl:template match="cliente">

	</xsl:template>

</xsl:stylesheet>