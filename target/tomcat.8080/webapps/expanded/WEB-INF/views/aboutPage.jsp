
<div class="pg_about page">
    <div class="inner-page">
        <%--<h1>
            Add a Person
        </h1>

        <c:url var="addAction" value="/person/add" ></c:url>

        <form:form action="${addAction}" commandName="person">
            <table>
                <c:if test="${!empty person.name}">
                    <tr>
                        <td>
                            <form:label path="id">
                                <spring:message text="ID"/>
                            </form:label>
                        </td>
                        <td>
                            <form:input path="id" readonly="true" size="8"  disabled="true" />
                            <form:hidden path="id" />
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="Name"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="name" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="price">
                            <spring:message text="Price"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="price" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty product.name}">
                            <input type="submit"
                                   value="<spring:message text="Edit Person"/>" />
                        </c:if>
                        <c:if test="${empty product.name}">
                            <input type="submit"
                                   value="<spring:message text="Add Person"/>" />
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
        <br>--%>
        <h3>Persons List</h3>
        <c:if test="${!empty listPersons}">
            <table class="tg">
                <tr>
                    <th width="80">Person ID</th>
                    <th width="120">Person Name</th>
                    <th width="120">Person Country</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listPersons}" var="person">
                    <tr>
                        <td>${person.id}</td>
                        <td>${person.name}</td>
                        <td>${person.price}</td>
                        <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                        <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
