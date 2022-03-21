<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:strip-space elements="*" />

	<xsl:template match="albumes">
		<html>
		<head>
			<meta charset="utf-8" />
			<title>albumes</title>
			<link rel="stylesheet" href="albumes.css"/>
			</head>
			
			<body>
			<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="album">
		<table>
			<tr>
				<td colspan="3">TITULO:<xsl:value-of select="titulo"/>
					ARTISTA:<xsl:value-of select="titulo"/>
					GRUPO:<xsl:value-of select="grupo"/>
				</td>
			</tr>
			<tr>
				<td>titulo</td>
				<td>duracion</td>
				<td>letrista</td>
			</tr>
				<xsl:apply-templates/>
		</table>
	</xsl:template>

	<xsl:template match="canciones">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="titulo">
	</xsl:template>
	<xsl:template match="artista">
	</xsl:template>
	<xsl:template match="grupo">
	</xsl:template>
	<xsl:template match="discografica">
	</xsl:template>
	<xsl:template match="productor">
	</xsl:template>
	<xsl:template match="num_canciones">
	</xsl:template>
	<xsl:template match="duracion">
	</xsl:template>
	<xsl:template match="fecha_pub">
	</xsl:template>
	<xsl:template match="genero">
	</xsl:template>
	<xsl:template match="precio">
	</xsl:template>
	<xsl:template match="url_caratula">
	</xsl:template>
	<xsl:template match="formato">
	</xsl:template>

	<xsl:template match="cancion">
		<tr>
			<th><xsl:value-of select="titulo"/></th>
	
			<td><xsl:value-of select="duracion"/></td>
		
			<td><xsl:value-of select="letrista"/></td>
		</tr>
	</xsl:template>

</xsl:stylesheet>
