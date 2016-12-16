<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="view/header/title" />
				</title>
			</head>
			<body>
				<xsl:value-of select="view/body/form">
					<form>
						<xsl:attribute name="name">
	                       <xsl:value-of select="name" />                  
	                    </xsl:attribute>

						<xsl:attribute name="action">
						   <xsl:value-of select="action" />					
						</xsl:attribute>

						<xsl:attribute name="method">
	                       <xsl:value-of select="method" />                  
	                    </xsl:attribute>

						<table>
							<xsl:for-each select="textview">
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
								<td>
									<xsl:value-of select="bottonview/label" />
								</td>
							</button>
						</div>
					</form>
				</xsl:value-of>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>