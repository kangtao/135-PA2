<%@page
    import="java.util.List"
    import="helpers.*"
    import="java.sql.*"
    import = "java.util.*"
%>
<%
	List<CategoryWithCount> categories = CategoriesHelper.listCategories();
	List<defaultCustomerWithProducts> defaultList = AnalyticsHelper.defaultList(); 
	ArrayList<String> totalMoneyList = new ArrayList<String>();
	ArrayList<String> customerList = new ArrayList<String>();
	ArrayList<String> productsList = new ArrayList<String>();

	String cname = defaultList.get(0).getCname();
	int j =0;
	for (int i = 0; i < defaultList.size(); i++)
	{
		if(i == 0)
		{
			customerList.add(cname);
			productsList.add(defaultList.get(0).getPname());	
		}
		else if(defaultList.get(i).getCname().equals(defaultList.get(0).getCname()))
		{
		
			productsList.add(defaultList.get(i).getPname());			
		}
		else if(!defaultList.get(i).getCname().equals(cname))
		{
			//System.out.print("Goin");
			cname = defaultList.get(i).getCname();
			customerList.add(cname);			
		}
				
		totalMoneyList.add(Integer.toString(defaultList.get(i).getTotalMoney()));
		
	}
	
	
	ArrayList<String> customerTotalMoneyList = new ArrayList<String>();
	ArrayList<String> productsTotalMoneyList = new ArrayList<String>();
	int totalMoneyCustomer = 0;
	int totalMoneyProduct = 0;
	for (int i = 0; i < customerList.size(); i++) 
    {
		customerList.get(i);
		while(j < productsList.size())
		{
			totalMoneyCustomer = Integer.parseInt(totalMoneyList.get(i*productsList.size() + j)) + totalMoneyCustomer;
			j++;
		}
		customerTotalMoneyList.add(Integer.toString(totalMoneyCustomer));
		totalMoneyCustomer = 0;
		j = 0;
	
	} 
	j = 0;
	
	for(int i = 0; i < productsList.size();i++)
	{
		while(j < customerList.size())
		{
			totalMoneyProduct = Integer.parseInt(totalMoneyList.get(j*productsList.size() + i)) + totalMoneyProduct;
			j++;
		}
		productsTotalMoneyList.add(Integer.toString(totalMoneyProduct));
		totalMoneyProduct = 0;
		j = 0;
		
	}
	
%>

<table>
  <tr>
  	 <td>
  	 	Rows dropdown menu &nbsp&nbsp&nbsp&nbsp
  	 </td>
     <td>
     	<select id="RowsDropdown" name="RowsDropdown">
        	<option>Customers</option>
            <option>States</option>
       </select>
     </td>
  </tr>
  <tr>
  	 <td>
  	 	Columns dropdown menu &nbsp&nbsp&nbsp&nbsp
  	 </td>
     <td>
     	<select id="ColumnsDropdown" name="ColumnsDropdown">
     		<option>None</option>
        	<option>Alphabetical</option>
            <option>Top-K</option>
       </select>
     </td>
  </tr>
  <tr>
  	<td>
  	 	Categories dropdown menu &nbsp&nbsp&nbsp&nbsp
  	 </td>
  	<td>
     	<select id="CategoriesDropdown" name="CategoriesDropdown">
     		<option>None</option>
     		<% 
     			for(CategoryWithCount c: categories)
     			{
     		%>
        	<option><%=c.getName()%></option>
        	<%
        		} 
        	%>
        	
       </select>
     </td>
  <tr>
  </tr>
</table>
</br>

<table
    class="table table-striped"
    align="center">
    <thead>
    	<tr align="center">
    		<th>&nbsp</th>
    		<%
    			for (int i = 0; i < productsList.size(); i++) 
    		    {
    		%>
    		<th>
   				<%=productsList.get(i)%>
   				<%="("+productsTotalMoneyList.get(i)+")"%>
    		</th>
    		<% 
    			}
    		%>  		
    	<tr>
    </thead>
    <tbody> 
    		<%
    			for (int i = 0; i < customerList.size(); i++) 
    		    {
    				
    		%>
    		<tr>
    			<td>
   					<%=customerList.get(i)%>
   					<%="("+customerTotalMoneyList.get(i)+")"%>
   				</td>
   					<% while(j < productsList.size())
   					   {
   					%>
   					<td>	<%=totalMoneyList.get(i*productsList.size() + j) %>
   					
   					</td>
   					<%
   							j++;
   					   }
   					   j = 0;
   					%>
   	   		<% 
    			}
    		%>  
    				
    		</tr>

    </tbody>
</table>
