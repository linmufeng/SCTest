<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method='html' version='1.0' encoding='UTF-8'
		indent='yes' />

	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="view/header/title" />
				</title>
			</head>
			<body>
				<form>
					<xsl:attribute name="name">
	                       <xsl:value-of select="view/body/form/name" />                  
	                    </xsl:attribute>

					<xsl:attribute name="action">
						   <xsl:value-of select="view/body/form/action" />					
						</xsl:attribute>

					<xsl:attribute name="method">
	                       <xsl:value-of select="view/body/form/method" />                  
	                    </xsl:attribute>

					<table>
						<xsl:for-each select="view/body/form/textview">
							<tr>
								<td>
									<xsl:value-of select="label" />
								</td>
								<td>
									<xsl:value-of select="value" />
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<div>
						<button>
							<xsl:for-each select="view/body/form/buttonview[label='Logout']">
								<xsl:value-of select="label" />
							</xsl:for-each>
						</button>
					</div>
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>