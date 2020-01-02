<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:48pt; font-family:arial; color:#990000; font-weight:bold">Language Detection System - Ultan Kearns G00343745</div>

</p>&nbsp;</p>&nbsp;</p>

<table width="600" cellspacing="0" cellpadding="7" border="0">
	<tr>
		<td valign="top">

			<form bgcolor="white" method="POST" action="doProcess">
				<fieldset>
					<legend><h3>Enter your chosen language in the textarea below and hit Detect Language to add it to job queue</h3></legend>
				
					<b>Set Kmer size:</b>
						
					<select name="cmbOptions">
						<option>1</option>
						<option selected>2</option>
						<option>3</option>
					</select>	
					<p/>

					<b>Enter Text :</b><br>
					<textarea name="query" rows="10" cols="100"  wrap="soft"></textarea>	
					<p/>

					<center><input type="submit" value="Detect Language"></center>
				</fieldset>							
			</form>	

		</td>
	</tr>
</table>
<%@ include file="includes/footer.jsp" %>

