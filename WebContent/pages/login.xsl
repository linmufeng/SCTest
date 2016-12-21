<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:temple match="/">
		<html>
			<center>
				<div>
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
							<xsl:for-each select="view/body/form/inputview[name='inputUserName']">
								<tr>
									<td width="70" align="right">
										<font size="3">用户名：</font>
									</td>
									<td colspan="2">
										<input type="text" style="width: 200, height:30;">
											<xsl:attribute name="value">
                                            <xsl:value-of
												select="value"></xsl:value-of>
                                        </xsl:attribute>
										</input>
									</td>
								</tr>

							</xsl:for-each>
							<xsl:for-each select="view/body/form/inputview[name='inputPassword']">
								<tr>
									<td width="70" align="right">
										<font size="3">密 码：</font>
									</td>
									<td colspan="2">
										<input type="text" style="width: 200, height:30;">
											<xsl:attribute name="value">
                                            <xsl:value-of
												select="value"></xsl:value-of>
                                        </xsl:attribute>
										</input>
									</td>
								</tr>

							</xsl:for-each>

							<tr>
								<td colspan="3" align="center">
									<input type="submit" style="width: 140, height:20">
										<xsl:attribute name="value">
										<xsl:value-of
											select="value" /> </xsl:attribute>
									</input>
								</td>
								<td>
									<font color="red"> ${msg} </font>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</center>
		</html>
	</xsl:temple>

</xsl:stylesheet>