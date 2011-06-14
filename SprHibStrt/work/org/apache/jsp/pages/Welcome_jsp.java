package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.lbr.dao.hibernate.domain.*;
import com.lbr.SubcategoryWrapper;
import com.lbr.LbrConstants;
import com.lbr.LbrUtility;
import com.lbr.web.struts.form.UserPreferenceForm;
import com.lbr.web.struts.action.*;
import com.lbr.web.struts.form.*;
import java.util.*;
import com.lbr.dao.hibernate.domain.*;
import com.lbr.core.*;

public final class Welcome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

String prefStatus;
  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(6);
    _jspx_dependants.add("/WEB-INF/struts-bean.tld");
    _jspx_dependants.add("/WEB-INF/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/struts-logic.tld");
    _jspx_dependants.add("/pages/top.jsp");
    _jspx_dependants.add("/pages/user/userPreferences.jsp");
    _jspx_dependants.add("/pages/user/../calendarStatic.html");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fredirect_005fforward_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005ferrors_005fproperty_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhtml_005flocale;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fbase_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fnotPresent_005fscope_005fname;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fpresent_005fname;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fform_005fmethod_005faction;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty_005fonchange;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005foption_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fselect_005fsize_005fproperty_005fonchange_005fmultiple;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005flink_005faction;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005flogic_005fredirect_005fforward_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fproperty_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fhtml_005flocale = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fbase_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fnotPresent_005fscope_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fpresent_005fname = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fform_005fmethod_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty_005fonchange = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005foption_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fselect_005fsize_005fproperty_005fonchange_005fmultiple = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fhtml_005flink_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005flogic_005fredirect_005fforward_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fproperty_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fhtml_005flocale.release();
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fbase_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.release();
    _005fjspx_005ftagPool_005flogic_005fnotPresent_005fscope_005fname.release();
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.release();
    _005fjspx_005ftagPool_005flogic_005fpresent_005fname.release();
    _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005fid.release();
    _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fform_005fmethod_005faction.release();
    _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty_005fonchange.release();
    _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.release();
    _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005fselect_005fsize_005fproperty_005fonchange_005fmultiple.release();
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.release();
    _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty.release();
    _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.release();
    _005fjspx_005ftagPool_005fhtml_005flink_005faction.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--");
if(request.getSession().getAttribute("USERVO")==null) {
      out.write(" \r\n");
      out.write("\t");
      if (_jspx_meth_logic_005fredirect_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
 } 
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t-->\r\n");
      out.write("\t<font color=\"red\">");
      if (_jspx_meth_html_005ferrors_005f0(_jspx_page_context))
        return;
      out.write("</font>\r\n");
      //  html:html
      org.apache.struts.taglib.html.HtmlTag _jspx_th_html_005fhtml_005f0 = (org.apache.struts.taglib.html.HtmlTag) _005fjspx_005ftagPool_005fhtml_005fhtml_005flocale.get(org.apache.struts.taglib.html.HtmlTag.class);
      _jspx_th_html_005fhtml_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fhtml_005f0.setParent(null);
      _jspx_th_html_005fhtml_005f0.setLocale(true);
      int _jspx_eval_html_005fhtml_005f0 = _jspx_th_html_005fhtml_005f0.doStartTag();
      if (_jspx_eval_html_005fhtml_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("<head>\r\n");
          out.write("<title>");
          if (_jspx_meth_bean_005fmessage_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("</title>\r\n");
          if (_jspx_meth_html_005fbase_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("</head>\r\n");
          out.write("<body bgcolor=\"white\">\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("          \r\n");
          out.write("          \r\n");
          out.write("<LINK rel=\"stylesheet\" type=\"text/css\" name=\"style\" href=\"");
          if (_jspx_meth_html_005frewrite_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\">\r\n");
          out.write("<LINK rel=\"stylesheet\" type=\"text/css\" name=\"base\" href=\"");
          if (_jspx_meth_html_005frewrite_005f1(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\">\r\n");
          out.write("\r\n");
          out.write("<table class=\"headerTable\"> \r\n");
          out.write("\t\t<tr>\r\n");
          out.write("\t\t\t<td align=\"left\" colspan=\"4\" width=\"100%\">\r\n");
          out.write("\t\t\t\t\t<h1 style=\"color: #660033\"><font size=\"6\" face=\"Monotype Corsiva\">LBRS</font></h1>\t\r\n");
          out.write("\t\t\t\t\t<!--  \r\n");
          out.write("\t\t\t\t\t<p style=\"color: #660033\"><font size=\"8\" face=\"Monotype Corsiva\">Location Based Recommendation System</font></p>\r\n");
          out.write("\t\t\t\t\t-->\t\r\n");
          out.write("\t\t\t</td>\t\t\t     \r\n");
          out.write(" \t\t</tr>\r\n");
          out.write(" \t\t");
 
 		Users user =(Users)session.getAttribute("USERVO");
 		
          out.write("\r\n");
          out.write(" \t\t");
 if(user== null) {
          out.write("\r\n");
          out.write("          <tr>\r\n");
          out.write("          ");
          out.write("\r\n");
          out.write("\t\t\t    <td class=\"headerTableRowData\" colspan=\"2\">\r\n");
          out.write("                     ");
          if (_jspx_meth_html_005flink_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t<td class=\"headerTableRowData\"  colspan=\"2\">\r\n");
          out.write("\t\t\t\t\t");
          if (_jspx_meth_html_005flink_005f1(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t</td>\r\n");
          out.write("        </tr>\r\n");
          out.write("         ");
} else {
          out.write("\r\n");
          out.write("          <tr>\r\n");
          out.write("\t\t          ");
 if(user.getUserpermissions().getBasicModulePermission().booleanValue()) {
          out.write("\r\n");
          out.write("\t\t\t\t\t    <td class=\"headerTableRowData\">\r\n");
          out.write("\t\t\t\t\t\t\t");
          if (_jspx_meth_html_005flink_005f2(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t");
          out.write("\t\t\t\t\t\t\t\t\t\r\n");
          out.write("\t\t                <td class=\"headerTableRowData\" colspan=\"2\">\r\n");
          out.write("\t\t\t\t\t\t\t \t");
          if (_jspx_meth_html_005flink_005f3(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write(" \r\n");
          out.write("\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t\t\t<td class=\"headerTableRowData\">\r\n");
          out.write("\t\t                      \t\t");
          if (_jspx_meth_html_005flink_005f4(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write(" \r\n");
          out.write("\t\t\t\t\t\t</td>\t\t\t\t\t\t\r\n");
          out.write("\t\t\t\t  ");
 } else { 
          out.write("\t\t\r\n");
          out.write("\t\t                <td class=\"headerTableRowData\"  colspan=\"2\">\r\n");
          out.write("\t\t\t\t\t\t\t \t");
          if (_jspx_meth_html_005flink_005f5(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write(" \r\n");
          out.write("\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t\t\t<td class=\"headerTableRowData\" colspan=\"2\">\r\n");
          out.write("\t\t                      \t\t");
          if (_jspx_meth_html_005flink_005f6(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write(" \r\n");
          out.write("\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t ");
 } 
          out.write("\t\r\n");
          out.write("        </tr>        \r\n");
          out.write("         ");
 } 
          out.write("\r\n");
          out.write("       \r\n");
          out.write("\t\t");
 if(user!=null && user.getUserpermissions().getEventsModulePermission().booleanValue()) {
          out.write("\t\t\r\n");
          out.write("\t\t\t <tr> \t\t\r\n");
          out.write("\t\t\t\t<td class=\"eventheaderTableRowData\" colspan=\"2\">\r\n");
          out.write("\t\t\t\t\t");
          if (_jspx_meth_html_005flink_005f7(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t<td class=\"eventheaderTableRowData\" colspan=\"2\">\r\n");
          out.write("\t\t\t\t");
          out.write("\r\n");
          out.write("\t\t\t\t\t");
          if (_jspx_meth_html_005flink_005f8(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("\t\t\t\t</td>\t\r\n");
          out.write("\t\t\t</tr>\t\t\t\r\n");
          out.write("\t\t");
 } 
          out.write("\t\t\t        \r\n");
          out.write("              \r\n");
          out.write("\t\t<tr> <td colspan=\"4\">&nbsp;</td></tr>\r\n");
          out.write("</table>\r\n");
          out.write("  \r\n");
          out.write("\t  \r\n");
          out.write("  \r\n");
          out.write("\r\n");
          if (_jspx_meth_logic_005fnotPresent_005f0(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("\r\n");
          out.write("<!-- \r\n");
          out.write("<font color=\"red\">");
          if (_jspx_meth_html_005ferrors_005f1(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("</font>\r\n");
          out.write(" -->\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("\r\n");
          out.write("<script type=\"text/javascript\" language=\"javascript\" src=\"ajax.js\"></script>\r\n");
          out.write("<script type=\"text/javascript\" language=\"javascript\" src=\"lbr.js\"></script>\r\n");
          out.write("<font color=\"red\">");
          if (_jspx_meth_html_005ferrors_005f2(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("</font>\r\n");
          out.write("<h2>\r\n");

	String userName = ((Users) request.getSession().getAttribute(
			"USERVO")).getUserName();
    Users curruser = LbrAction.getThreadLocalUserValue();
    boolean fullDebugON = LbrConstants.LBR_DEBUG && (curruser.getUserpermissions().getUserTypeId() == LbrConstants.ADMIN_USERTYPE_ID);

          out.write(" &nbsp;&nbsp;");
          out.print(userName + "'s ");
          out.write(' ');
          if (_jspx_meth_bean_005fmessage_005f1(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write("</h2>\r\n");
          out.write("\r\n");
          //  logic:present
          org.apache.struts.taglib.logic.PresentTag _jspx_th_logic_005fpresent_005f0 = (org.apache.struts.taglib.logic.PresentTag) _005fjspx_005ftagPool_005flogic_005fpresent_005fname.get(org.apache.struts.taglib.logic.PresentTag.class);
          _jspx_th_logic_005fpresent_005f0.setPageContext(_jspx_page_context);
          _jspx_th_logic_005fpresent_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
          _jspx_th_logic_005fpresent_005f0.setName("messages");
          int _jspx_eval_logic_005fpresent_005f0 = _jspx_th_logic_005fpresent_005f0.doStartTag();
          if (_jspx_eval_logic_005fpresent_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("\t<font color=\"blue\" size=\"1\"> ");
              //  logic:iterate
              org.apache.struts.taglib.logic.IterateTag _jspx_th_logic_005fiterate_005f0 = (org.apache.struts.taglib.logic.IterateTag) _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005fid.get(org.apache.struts.taglib.logic.IterateTag.class);
              _jspx_th_logic_005fiterate_005f0.setPageContext(_jspx_page_context);
              _jspx_th_logic_005fiterate_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fpresent_005f0);
              _jspx_th_logic_005fiterate_005f0.setId("msg");
              _jspx_th_logic_005fiterate_005f0.setName("messages");
              int _jspx_eval_logic_005fiterate_005f0 = _jspx_th_logic_005fiterate_005f0.doStartTag();
              if (_jspx_eval_logic_005fiterate_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                java.lang.Object msg = null;
                if (_jspx_eval_logic_005fiterate_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.pushBody();
                  _jspx_th_logic_005fiterate_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                  _jspx_th_logic_005fiterate_005f0.doInitBody();
                }
                msg = (java.lang.Object) _jspx_page_context.findAttribute("msg");
                do {
                  out.write("\r\n");
                  out.write("\t\t");
                  if (_jspx_meth_bean_005fwrite_005f0(_jspx_th_logic_005fiterate_005f0, _jspx_page_context))
                    return;
                  out.write("\r\n");
                  out.write("\t\t<br>\r\n");
                  out.write("\t");
                  int evalDoAfterBody = _jspx_th_logic_005fiterate_005f0.doAfterBody();
                  msg = (java.lang.Object) _jspx_page_context.findAttribute("msg");
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
                if (_jspx_eval_logic_005fiterate_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                  out = _jspx_page_context.popBody();
                }
              }
              if (_jspx_th_logic_005fiterate_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005fid.reuse(_jspx_th_logic_005fiterate_005f0);
                return;
              }
              _005fjspx_005ftagPool_005flogic_005fiterate_005fname_005fid.reuse(_jspx_th_logic_005fiterate_005f0);
              out.write(" </font>\r\n");
              int evalDoAfterBody = _jspx_th_logic_005fpresent_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_logic_005fpresent_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005flogic_005fpresent_005fname.reuse(_jspx_th_logic_005fpresent_005f0);
            return;
          }
          _005fjspx_005ftagPool_005flogic_005fpresent_005fname.reuse(_jspx_th_logic_005fpresent_005f0);
          out.write("\r\n");
          out.write("\r\n");

	UserPreferenceForm prefForm = (UserPreferenceForm) session
			.getAttribute("UserPreferenceForm");

          out.write('\r');
          out.write('\n');
          //  html:form
          org.apache.struts.taglib.html.FormTag _jspx_th_html_005fform_005f0 = (org.apache.struts.taglib.html.FormTag) _005fjspx_005ftagPool_005fhtml_005fform_005fmethod_005faction.get(org.apache.struts.taglib.html.FormTag.class);
          _jspx_th_html_005fform_005f0.setPageContext(_jspx_page_context);
          _jspx_th_html_005fform_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
          _jspx_th_html_005fform_005f0.setAction("/UserPreference");
          _jspx_th_html_005fform_005f0.setMethod("post");
          int _jspx_eval_html_005fform_005f0 = _jspx_th_html_005fform_005f0.doStartTag();
          if (_jspx_eval_html_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("<div style=\"width: 100; position: relative; left: 5px; top: 10px;\">\r\n");
              out.write("\r\n");
              out.write("\t<input type=\"hidden\" name=\"formAction\" value=\"\" />\r\n");
              out.write("\t");
              out.write("\r\n");
              out.write("\t<table border=\"6\">\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmallNoWrap\">\r\n");
              out.write("\t\t\t<td><b>Select Category :</b></td>\r\n");
              out.write("\t\t\t<td><!-- \r\n");
              out.write("\t\t\tname = form name as in <form-bean name=\"UserPreferenceForm\"  ...struts-config.xml \r\n");
              out.write("\t\t\tproperty = attribute of type=\"com.lbr.web.struts.form.UserPreferenceForm\" \r\n");
              out.write("\t\t\tlabel = attribute of ObjectType stored in the collection ...CategoryData here\r\n");
              out.write("\t\t\tvalue= attribute of ObjectType stored in the collection...CategoryData here\r\n");
              out.write("\t\t\t --> ");
              if (_jspx_meth_html_005fselect_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr>\r\n");
              out.write("\t\t\t<td width=\"50%\"><b>Select SubCategory :</b></td>\r\n");
              out.write("\t\t\t<td>");
              if (_jspx_meth_html_005fselect_005f1(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr />\r\n");
              out.write("\t\t<tr>\r\n");
              out.write("\t\t\t<td />\r\n");
              out.write("\t\t\t<td>");
              if (_jspx_meth_html_005fsubmit_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmall\">\r\n");
              out.write("\t\t\t<td />\r\n");
              out.write("\t\t\t<td>");
              if (_jspx_meth_html_005fsubmit_005f1(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmall\">\r\n");
              out.write("\t\t\t<td width=\"50%\"><b>Vicinity Policy : </b><img alt=\"help\" height=\"16\" width=\"16\" src=\"../images/help.jpg\" TITLE=\"Recommendations can be generated for events within the PINCode/City/District/State\"></td>\r\n");
              out.write("\t\t\t<td>");
              if (_jspx_meth_html_005fselect_005f2(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr>\r\n");
              out.write("\t\t\t<td><b>Select Date Range : </b><img alt=\"help\" height=\"16\" width=\"16\" \r\n");
              out.write("\t\t\t\tsrc=\"../images/help.jpg\"\r\n");
              out.write("\t\t\t\tTITLE=\"Start and end dates for events you are interested in. e.g 2011-01-28 10:00 , 2011-01-31 20:00\"></td>\r\n");
              out.write("\t\t\t<td>");
              if (_jspx_meth_html_005ftext_005f0(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t<a onclick=\"CallCalendarMethod('startDate', 'anchor18');\"\r\n");
              out.write("\t\t\t\tname=\"anchor18\" id=\"anchor18\"><img src=\"../images/calendar.jpg\"\r\n");
              out.write("\t\t\t\theight=\"20\" width=\"30\" /></a></td>\r\n");
              out.write("\t\t\t</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmallNoWrap\">\r\n");
              out.write("\t\t\t<td />\r\n");
              out.write("\t\t\t<td>");
              if (_jspx_meth_html_005ftext_005f1(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t<a onclick=\"CallCalendarMethod('endDate', 'anchor19');\"\r\n");
              out.write("\t\t\t\tname=\"anchor19\" id=\"anchor19\"><img src=\"../images/calendar.jpg\"\r\n");
              out.write("\t\t\t\theight=\"20\" width=\"30\" /></a></td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<!-- \r\n");
              out.write("\t     <tr class=\"spaceUnderSmall\">   \r\n");
              out.write("\t        <td><b>My Current Location :</b><br/>(recommendations will be<br/>generated in the vicinity of<br/>this location:) ");
if (prefForm != null) {
              out.write("\r\n");
              out.write("\t        \t\t\t\t\t\t");
              out.print(prefForm.getCurrentLocationStr());
              out.write("\r\n");
              out.write("\t        \t\t\t\t\t\t");
}
              out.write("\r\n");
              out.write("\t        </td>\r\n");
              out.write("\t    \t<td>\r\n");
              out.write("\t           \t");
              if (_jspx_meth_html_005flink_005f9(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t        </td>\r\n");
              out.write("\t    </tr>\t     \r\n");
              out.write("\t      -->\r\n");
              out.write("\r\n");
              out.write("\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmall\">\r\n");
              out.write("\t\t\t<td><b>My Current Location :</b><img alt=\"help\" height=\"16\" width=\"16\" \r\n");
              out.write("\t\t\t\tsrc=\"../images/help.jpg\"\r\n");
              out.write("\t\t\t\tTITLE=\"Recommendations will be generated in the vicinity of this location\">\r\n");
              out.write("\t\t\t</td>\r\n");
              out.write("\t\t\t<td class=\"locationData\">\r\n");
              out.write("\t\t\t");

				if (prefForm != null) {
			
              out.write(' ');
              out.print(prefForm.getCurrentLocationStr());
              out.write(' ');

 	}
 
              out.write("\r\n");
              out.write("\t\t\t</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr class=\"nowrap\">\r\n");
              out.write("\t\t\t<td>\r\n");
              out.write("\t\t\t    ");
              if (_jspx_meth_html_005flink_005f10(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t</td>\r\n");
              out.write("\t\t\t<td>\r\n");
              out.write("\t\t\t<img alt=\"help\" height=\"16\" width=\"16\" src=\"../images/help.jpg\" TITLE=\"Use the location helper to select your location. Recommendation for events will be generated in this location's vicinity.\">\r\n");
              out.write("\t\t\t</td>\t\t\t\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr>\r\n");
              out.write("\t\t\t");

				if (request.getParameter("formAction") != null
							&& !((String) request.getParameter("formAction"))
									.equals("hideRecommend")
							&& (prefForm != null
									&& prefForm.getRecommendations() != null && prefForm
									.getRecommendations().size() > 0)) {
			
              out.write("\r\n");
              out.write("\t\t\t<td colspan=\"1\">");
              if (_jspx_meth_html_005fsubmit_005f2(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmall\">\r\n");
              out.write("\t\t\t<td colspan=\"1\">");
              if (_jspx_meth_html_005fsubmit_005f3(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t</td>\r\n");
              out.write("\t\t</tr>\r\n");
              out.write("\t\t");

			} else {
		
              out.write("\r\n");
              out.write("\t\t<tr class=\"spaceUnderSmall\">\r\n");
              out.write("\t\t\t<td colspan=\"1\">");
              if (_jspx_meth_html_005fsubmit_005f4(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("</td>\r\n");
              out.write("\t\t\t");

				}
			
              out.write("\r\n");
              out.write("\t    </tr>\r\n");
              out.write("\t</table>\r\n");
              out.write("</div>\r\n");
              out.write("\r\n");
              out.write("<div id=\"myDiv\"\r\n");
              out.write("\tstyle=\"position: absolute; width: 300px; height: 150px; top: 140px; left: 500px;\"\r\n");
              out.write("\ttype=\"hidden\">\r\n");

	if (prefForm != null && prefForm.getUserPreferencesWithLevels() != null) {

              out.write("\r\n");
              out.write("<h3>");
              if (_jspx_meth_bean_005fmessage_005f9(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.print(prefForm.getUserPreferencesWithLevels().size());
              out.write("\r\n");
              out.write("</h3>\r\n");
              out.write("<table cellpadding=\"5\" cellspacing=\"2\" border=\"6\" width=\"300\">\r\n");
              out.write("\t\r\n");
              out.write("\t<tr>\r\n");
              out.write("\t\t<th>Category</th>\r\n");
              out.write("\t\t<th>SubCategory</th>\r\n");
              out.write("\t\t<th>Level</th>\r\n");
              out.write("\t</tr>\r\n");
              out.write("\r\n");
              out.write("\t");

			List<SubcategoryWrapper> currUserPreferences = prefForm.getUserPreferencesWithLevels();
			int counter =0;
			String[] userprefLevels = prefForm.getSubcatLevels();
			for (Iterator iterator = currUserPreferences.iterator(); iterator.hasNext();) {
				//String currSubCatUserPrefLevel = userprefLevels[counter++];
				SubcategoryWrapper subcatwrap = (SubcategoryWrapper) iterator.next();
				Subcategory subcat = subcatwrap.getUserPreference();
				Category cat = subcat.getCategory();
	
              out.write("\r\n");
              out.write("\t<tr/>\r\n");
              out.write("\t<tr>\r\n");
              out.write("\t\t<td>");
              out.print(cat.getCatName());
              out.write(' ');
if(fullDebugON) {
              out.write('[');
              out.print(cat.getCatId());
              out.write(']');
              out.write(' ');
}
              out.write("</td>\r\n");
              out.write("\t\t<td>");
              out.print(subcat.getSubCatName());
              out.write(' ');
if(fullDebugON) {
              out.write(' ');
              out.write('[');
              out.print(subcat.getSubCatId());
              out.write(']');
}
              out.write("</td>\r\n");
              out.write("\t\t<td>\r\n");
              out.write("\t\t\t<select name=\"subcatLevels\" onchange=\"\">\r\n");
              out.write("\t\t\t<option value=\"1\"");
 if(subcatwrap.getLevel()==1) {
              out.write(" SELECTED  ");
} 
              out.write(" >Very Low</option>\r\n");
              out.write("\t\t\t<option value=\"2\"");
 if(subcatwrap.getLevel()==2) {
              out.write(" SELECTED  ");
} 
              out.write(" >Low</option>\r\n");
              out.write("\t\t\t<option value=\"3\"");
 if(subcatwrap.getLevel()==3) {
              out.write(" SELECTED  ");
} 
              out.write(" >Medium</option>\r\n");
              out.write("\t\t\t<option value=\"4\"");
 if(subcatwrap.getLevel()==4) {
              out.write(" SELECTED  ");
} 
              out.write(" >High</option>\r\n");
              out.write("\t\t\t<option value=\"5\"");
 if(subcatwrap.getLevel()==5) {
              out.write(" SELECTED  ");
} 
              out.write(" >Very High</option></select>\r\n");
              out.write("\t    </td>\t\r\n");
              out.write("\t</tr>\r\n");
              out.write("\t\r\n");
              out.write("\t");

		}
	
              out.write("\r\n");
              out.write("\t<tr/><tr/>\r\n");
              out.write("\t<tr class=\"tablerowButton\">\r\n");
              out.write("\t<td/><td/>\r\n");
              out.write("\t\t<td>\r\n");
              out.write("\t\t<table cellpadding=\"5\" cellspacing=\"2\" border=\"6\">\r\n");
              out.write("\t\t\t");
              if (_jspx_meth_html_005fsubmit_005f5(_jspx_th_html_005fform_005f0, _jspx_page_context))
                return;
              out.write("\r\n");
              out.write("\t\t\t</table>\r\n");
              out.write("\t\t</td>\r\n");
              out.write("\t</tr>\t\r\n");
              out.write("</table>\r\n");

	} else {
		System.out.println("UserPreference is null  --> Not specified");

              out.write(" My current Preferences (total): 0 ");

	}

              out.write("\r\n");
              out.write("\r\n");
              int evalDoAfterBody = _jspx_th_html_005fform_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_html_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fhtml_005fform_005fmethod_005faction.reuse(_jspx_th_html_005fform_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fhtml_005fform_005fmethod_005faction.reuse(_jspx_th_html_005fform_005f0);
          out.write("\r\n");
          out.write("</div>\r\n");
          out.write("\r\n");
          out.write("<div id=\"recoDiv\" style=\"position: absolute; left: 850px; top: 130px;\">\r\n");
          out.write("\r\n");

	if (prefForm != null
			&& prefForm.getRecommendations() != null
			&& request.getParameter("formAction") != null
			&& !((String) request.getParameter("formAction"))
					.equals("hideRecommend")) {

          out.write("\r\n");
          out.write("\r\n");
          out.write("<h3>");
          if (_jspx_meth_bean_005fmessage_005f11(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.print(prefForm.getRecommendations().size());
          out.write("</h3>\r\n");
          out.write("(Generated as of System state at: ");
          out.print(RecommendationEngine.lbrRecommendations
						.getGeneratedOn());
          out.write(')');
          out.write(' ');

	if (prefForm.getRecommendations().size() > 0) {

          out.write("\r\n");
          out.write("<table cellpadding=\"5\" cellspacing=\"2\" border=\"6\" width=\"510\">\r\n");
          out.write("\t<tr>\r\n");
          out.write("\t\t<th>#</th>\r\n");
          out.write("\t\t<th>Recommended Event</th>\r\n");
          out.write("\t</tr>\r\n");
          out.write("\r\n");
          out.write("\t");

		int ii = 0;
				List<EventRecommendationVO> userRecos = prefForm
						.getRecommendations();
				for (Iterator iterator = userRecos.iterator(); iterator
						.hasNext();) {
					EventRecommendationVO eventreco = (EventRecommendationVO) iterator
							.next();
					Events event = eventreco.getEvent();
	
          out.write("\r\n");
          out.write("\t<tr>\r\n");
          out.write("\t\t<td>");
          out.print(++ii);
          out.write("</td>\r\n");
          out.write("\t\t<td>");
          out.print(LbrUtility.printEventForHTML(event));
          out.write("</td>\r\n");
          out.write("\t</tr>\r\n");
          out.write("\t");

		}
			}
	
          out.write("\r\n");
          out.write("</table>\r\n");

	}
	if (prefForm.getRecommendations() == null
			|| prefForm.getRecommendations().size() == 0) {
		System.out.println("No recommendation for User !!");
		if (prefForm.getFormAction() != null
				&& prefForm.getFormAction().equalsIgnoreCase(
						"recommend")) {
			prefForm.setFormAction(null);

          out.write(" <br />\r\n");
          if (_jspx_meth_bean_005fmessage_005f12(_jspx_th_html_005fhtml_005f0, _jspx_page_context))
            return;
          out.write(' ');

 	}
 	}
 
          out.write("\r\n");
          out.write("</div>\r\n");
          out.write("<LINK REL=StyleSheet TYPE=\"text/css\" HREF=\"../css/calendar.css\" MEDIA=screen>\r\n");
          out.write("<script type=\"text/javascript\" language=\"javascript\" src=\"CalendarPopup.js\"></script>\r\n");
          out.write("<form>\r\n");
          out.write("<script language=\"JavaScript\" id=\"js18\">\r\n");
          out.write("var cal18 = new CalendarPopup(\"testdiv1\");\r\n");
          out.write("cal18.setCssPrefix(\"TEST\");\r\n");
          out.write("</script>\r\n");
          out.write("\r\n");
          out.write("<div id=\"testdiv1\" style=\"position: absolute; z-index: 10; visibility: hidden; background-color: red; top: 356px; left: 400px;\">\r\n");
          out.write("                          \r\n");
          out.write("<table class=\"TESTcpBorder\" borderwidth=\"1\" width=\"144\" border=\"1\" cellpadding=\"1\" cellspacing=\"0\">\r\n");
          out.write("<tbody><tr><td align=\"CENTER\">\r\n");
          out.write("<center>\r\n");
          out.write("<table borderwidth=\"0\" width=\"144\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr>\r\n");
          out.write("<td class=\"TESTcpMonthNavigation\" width=\"22\"><a class=\"TESTcpMonthNavigation\" href=\"javascript:CP_refreshCalendar(3,1,2011);\">&lt;&lt;</a></td>\r\n");
          out.write("<td class=\"TESTcpMonthNavigation\" width=\"100\"><span class=\"TESTcpMonthNavigation\">February 2011</span></td>\r\n");
          out.write("<td class=\"TESTcpMonthNavigation\" width=\"22\"><a class=\"TESTcpMonthNavigation\" href=\"javascript:CP_refreshCalendar(3,3,2011);\">&gt;&gt;</a></td>\r\n");
          out.write("</tr></tbody></table>\r\n");
          out.write("\r\n");
          out.write("<table class=\"calendarContentTable\">\r\n");
          out.write("<tbody><tr>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">S</span></td>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">M</span></td>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">T</span></td>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">W</span></td>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">T</span></td>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">F</span></td>\r\n");
          out.write("<td class=\"TESTcpDayColumnHeader\" width=\"14%\"><span class=\"TESTcpDayColumnHeader\">S</span></td>\r\n");
          out.write("</tr>\r\n");
          out.write("<tr>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,1,30);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">30</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,1,31);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">31</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,1);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">1</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,2);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">2</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,3);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">3</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,4);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">4</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,5);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">5</a></td>\r\n");
          out.write("</tr><tr>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,6);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">6</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,7);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">7</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,8);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">8</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,9);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">9</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,10);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">10</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,11);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">11</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,12);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">12</a></td>\r\n");
          out.write("</tr><tr>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,13);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">13</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,14);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">14</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,15);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">15</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,16);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">16</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,17);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">17</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,18);CP_hideCalendar('3');\" class=\"TESTcpCurrentDate\">18</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,19);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">19</a></td>\r\n");
          out.write("</tr><tr>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,20);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">20</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,21);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">21</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,22);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">22</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,23);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">23</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,24);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">24</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,25);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">25</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,26);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">26</a></td>\r\n");
          out.write("</tr><tr>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,27);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">27</a></td>\r\n");
          out.write("\t<td class=\"TESTcpCurrentMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,2,28);CP_hideCalendar('3');\" class=\"TESTcpCurrentMonthDate\">28</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,1);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">1</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,2);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">2</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,3);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">3</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,4);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">4</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,5);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">5</a></td>\r\n");
          out.write("</tr><tr>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,6);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">6</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,7);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">7</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,8);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">8</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,9);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">9</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,10);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">10</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,11);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">11</a></td>\r\n");
          out.write("\t<td class=\"TESTcpOtherMonthDate\"><a href=\"javascript:CP_tmpReturnFunction(2011,3,12);CP_hideCalendar('3');\" class=\"TESTcpOtherMonthDate\">12</a></td>\r\n");
          out.write("</tr><tr>\r\n");
          out.write("\t<td colspan=\"7\" class=\"TESTcpTodayText\" align=\"CENTER\">\r\n");
          out.write("\t\t<a class=\"TESTcpTodayText\" href=\"javascript:CP_tmpReturnFunction('2011','2','15');CP_hideCalendar('3');\">Today</a>\r\n");
          out.write("\t\t<br>\r\n");
          out.write("\t</td>\r\n");
          out.write("</tr></tbody></table>\r\n");
          out.write("</center></td></tr></tbody></table>\r\n");
          out.write("</div>\r\n");
          out.write("</form>");
          out.write("\r\n");
          out.write("<!-- \r\n");
          out.write(" <iframe style=\"position: absolute; left: 30px; top:500px;\" width=\"500\" height=\"400\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"http://maps.google.co.in/maps?hl=en&amp;q=salarpuria+hallmark+bangalore&amp;ie=UTF8&amp;hq=salarpuria+hallmark&amp;hnear=Bengaluru,+Karnataka&amp;ll=12.936939,77.691528&amp;spn=0.095363,0.181789&amp;z=13&amp;iwloc=A&amp;cid=474869173936973196&amp;output=embed\"></iframe><br /><small><a href=\"http://maps.google.co.in/maps?hl=en&amp;q=salarpuria+hallmark+bangalore&amp;ie=UTF8&amp;hq=salarpuria+hallmark&amp;hnear=Bengaluru,+Karnataka&amp;ll=12.936939,77.691528&amp;spn=0.095363,0.181789&amp;z=13&amp;iwloc=A&amp;cid=474869173936973196&amp;source=embed\" style=\"color:#0000FF;text-align:left\">View Larger Map</a></small>\r\n");
          out.write("   -->\r\n");
          out.write("\r\n");
          out.write("</body>\r\n");
          int evalDoAfterBody = _jspx_th_html_005fhtml_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_html_005fhtml_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fhtml_005fhtml_005flocale.reuse(_jspx_th_html_005fhtml_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fhtml_005fhtml_005flocale.reuse(_jspx_th_html_005fhtml_005f0);
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_logic_005fredirect_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  logic:redirect
    org.apache.struts.taglib.logic.RedirectTag _jspx_th_logic_005fredirect_005f0 = (org.apache.struts.taglib.logic.RedirectTag) _005fjspx_005ftagPool_005flogic_005fredirect_005fforward_005fnobody.get(org.apache.struts.taglib.logic.RedirectTag.class);
    _jspx_th_logic_005fredirect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_logic_005fredirect_005f0.setParent(null);
    _jspx_th_logic_005fredirect_005f0.setForward("userLogin");
    int _jspx_eval_logic_005fredirect_005f0 = _jspx_th_logic_005fredirect_005f0.doStartTag();
    if (_jspx_th_logic_005fredirect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flogic_005fredirect_005fforward_005fnobody.reuse(_jspx_th_logic_005fredirect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flogic_005fredirect_005fforward_005fnobody.reuse(_jspx_th_logic_005fredirect_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005ferrors_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:errors
    org.apache.struts.taglib.html.ErrorsTag _jspx_th_html_005ferrors_005f0 = (org.apache.struts.taglib.html.ErrorsTag) _005fjspx_005ftagPool_005fhtml_005ferrors_005fproperty_005fnobody.get(org.apache.struts.taglib.html.ErrorsTag.class);
    _jspx_th_html_005ferrors_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005ferrors_005f0.setParent(null);
    _jspx_th_html_005ferrors_005f0.setProperty("error.login.required");
    int _jspx_eval_html_005ferrors_005f0 = _jspx_th_html_005ferrors_005f0.doStartTag();
    if (_jspx_th_html_005ferrors_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005ferrors_005fproperty_005fnobody.reuse(_jspx_th_html_005ferrors_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fproperty_005fnobody.reuse(_jspx_th_html_005ferrors_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f0 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f0.setKey("welcome.title");
    int _jspx_eval_bean_005fmessage_005f0 = _jspx_th_bean_005fmessage_005f0.doStartTag();
    if (_jspx_th_bean_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005fbase_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:base
    org.apache.struts.taglib.html.BaseTag _jspx_th_html_005fbase_005f0 = (org.apache.struts.taglib.html.BaseTag) _005fjspx_005ftagPool_005fhtml_005fbase_005fnobody.get(org.apache.struts.taglib.html.BaseTag.class);
    _jspx_th_html_005fbase_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fbase_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    int _jspx_eval_html_005fbase_005f0 = _jspx_th_html_005fbase_005f0.doStartTag();
    if (_jspx_th_html_005fbase_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fbase_005fnobody.reuse(_jspx_th_html_005fbase_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fbase_005fnobody.reuse(_jspx_th_html_005fbase_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f0 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    _jspx_th_html_005frewrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005frewrite_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005frewrite_005f0.setPage("/css/style.css");
    int _jspx_eval_html_005frewrite_005f0 = _jspx_th_html_005frewrite_005f0.doStartTag();
    if (_jspx_th_html_005frewrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005frewrite_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:rewrite
    org.apache.struts.taglib.html.RewriteTag _jspx_th_html_005frewrite_005f1 = (org.apache.struts.taglib.html.RewriteTag) _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.get(org.apache.struts.taglib.html.RewriteTag.class);
    _jspx_th_html_005frewrite_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005frewrite_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005frewrite_005f1.setPage("/css/base.css");
    int _jspx_eval_html_005frewrite_005f1 = _jspx_th_html_005frewrite_005f1.doStartTag();
    if (_jspx_th_html_005frewrite_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005frewrite_005fpage_005fnobody.reuse(_jspx_th_html_005frewrite_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f0 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f0.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f0.setPage("/pages/user/forgetpassword.jsp");
    int _jspx_eval_html_005flink_005f0 = _jspx_th_html_005flink_005f0.doStartTag();
    if (_jspx_eval_html_005flink_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f0.doInitBody();
      }
      do {
        out.write("Forget Password");
        int evalDoAfterBody = _jspx_th_html_005flink_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f1 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f1.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f1.setPage("/pages/user/userlogin.jsp");
    int _jspx_eval_html_005flink_005f1 = _jspx_th_html_005flink_005f1.doStartTag();
    if (_jspx_eval_html_005flink_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f1.doInitBody();
      }
      do {
        out.write("Login");
        int evalDoAfterBody = _jspx_th_html_005flink_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f2 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f2.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f2.setPage("/UserPreference.do");
    int _jspx_eval_html_005flink_005f2 = _jspx_th_html_005flink_005f2.doStartTag();
    if (_jspx_eval_html_005flink_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f2.doInitBody();
      }
      do {
        out.write("Home");
        int evalDoAfterBody = _jspx_th_html_005flink_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f2);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f3 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f3.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f3.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f3.setPage("/UserRegister.do?action=update");
    int _jspx_eval_html_005flink_005f3 = _jspx_th_html_005flink_005f3.doStartTag();
    if (_jspx_eval_html_005flink_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f3.doInitBody();
      }
      do {
        out.write("Update Profile");
        int evalDoAfterBody = _jspx_th_html_005flink_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f3);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f4 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f4.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f4.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f4.setPage("/Logout.do");
    int _jspx_eval_html_005flink_005f4 = _jspx_th_html_005flink_005f4.doStartTag();
    if (_jspx_eval_html_005flink_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f4.doInitBody();
      }
      do {
        out.write("Logout");
        int evalDoAfterBody = _jspx_th_html_005flink_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f4);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f5 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f5.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f5.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f5.setPage("/UserRegister.do?action=update");
    int _jspx_eval_html_005flink_005f5 = _jspx_th_html_005flink_005f5.doStartTag();
    if (_jspx_eval_html_005flink_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f5.doInitBody();
      }
      do {
        out.write("Update Profile");
        int evalDoAfterBody = _jspx_th_html_005flink_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f5);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f6 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f6.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f6.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f6.setPage("/Logout.do");
    int _jspx_eval_html_005flink_005f6 = _jspx_th_html_005flink_005f6.doStartTag();
    if (_jspx_eval_html_005flink_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f6.doInitBody();
      }
      do {
        out.write("Logout");
        int evalDoAfterBody = _jspx_th_html_005flink_005f6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f6);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f7 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f7.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f7.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f7.setPage("/Events.do");
    int _jspx_eval_html_005flink_005f7 = _jspx_th_html_005flink_005f7.doStartTag();
    if (_jspx_eval_html_005flink_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f7.doInitBody();
      }
      do {
        out.write("Add Events");
        int evalDoAfterBody = _jspx_th_html_005flink_005f7.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f7);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f8 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f8.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_html_005flink_005f8.setStyleClass("headerTableRowDataLink");
    _jspx_th_html_005flink_005f8.setPage("/Events.do?modifyEvent=true");
    int _jspx_eval_html_005flink_005f8 = _jspx_th_html_005flink_005f8.doStartTag();
    if (_jspx_eval_html_005flink_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f8.doInitBody();
      }
      do {
        out.write("Modify Events");
        int evalDoAfterBody = _jspx_th_html_005flink_005f8.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005fstyleClass_005fpage.reuse(_jspx_th_html_005flink_005f8);
    return false;
  }

  private boolean _jspx_meth_logic_005fnotPresent_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  logic:notPresent
    org.apache.struts.taglib.logic.NotPresentTag _jspx_th_logic_005fnotPresent_005f0 = (org.apache.struts.taglib.logic.NotPresentTag) _005fjspx_005ftagPool_005flogic_005fnotPresent_005fscope_005fname.get(org.apache.struts.taglib.logic.NotPresentTag.class);
    _jspx_th_logic_005fnotPresent_005f0.setPageContext(_jspx_page_context);
    _jspx_th_logic_005fnotPresent_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_logic_005fnotPresent_005f0.setName("org.apache.struts.action.MESSAGE");
    _jspx_th_logic_005fnotPresent_005f0.setScope("application");
    int _jspx_eval_logic_005fnotPresent_005f0 = _jspx_th_logic_005fnotPresent_005f0.doStartTag();
    if (_jspx_eval_logic_005fnotPresent_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  <font color=\"red\">\r\n");
        out.write("    ERROR:  Application resources not loaded -- check servlet container\r\n");
        out.write("    logs for error messages.\r\n");
        out.write("  </font>\r\n");
        int evalDoAfterBody = _jspx_th_logic_005fnotPresent_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_logic_005fnotPresent_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flogic_005fnotPresent_005fscope_005fname.reuse(_jspx_th_logic_005fnotPresent_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flogic_005fnotPresent_005fscope_005fname.reuse(_jspx_th_logic_005fnotPresent_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005ferrors_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:errors
    org.apache.struts.taglib.html.ErrorsTag _jspx_th_html_005ferrors_005f1 = (org.apache.struts.taglib.html.ErrorsTag) _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.get(org.apache.struts.taglib.html.ErrorsTag.class);
    _jspx_th_html_005ferrors_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005ferrors_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    int _jspx_eval_html_005ferrors_005f1 = _jspx_th_html_005ferrors_005f1.doStartTag();
    if (_jspx_th_html_005ferrors_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.reuse(_jspx_th_html_005ferrors_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.reuse(_jspx_th_html_005ferrors_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005ferrors_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:errors
    org.apache.struts.taglib.html.ErrorsTag _jspx_th_html_005ferrors_005f2 = (org.apache.struts.taglib.html.ErrorsTag) _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.get(org.apache.struts.taglib.html.ErrorsTag.class);
    _jspx_th_html_005ferrors_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005ferrors_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    int _jspx_eval_html_005ferrors_005f2 = _jspx_th_html_005ferrors_005f2.doStartTag();
    if (_jspx_th_html_005ferrors_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.reuse(_jspx_th_html_005ferrors_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005ferrors_005fnobody.reuse(_jspx_th_html_005ferrors_005f2);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f1 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f1.setKey("label.UserPreferenceAction.message.dashboard");
    int _jspx_eval_bean_005fmessage_005f1 = _jspx_th_bean_005fmessage_005f1.doStartTag();
    if (_jspx_th_bean_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_bean_005fwrite_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_logic_005fiterate_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:write
    org.apache.struts.taglib.bean.WriteTag _jspx_th_bean_005fwrite_005f0 = (org.apache.struts.taglib.bean.WriteTag) _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.get(org.apache.struts.taglib.bean.WriteTag.class);
    _jspx_th_bean_005fwrite_005f0.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fwrite_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_logic_005fiterate_005f0);
    _jspx_th_bean_005fwrite_005f0.setName("msg");
    int _jspx_eval_bean_005fwrite_005f0 = _jspx_th_bean_005fwrite_005f0.doStartTag();
    if (_jspx_th_bean_005fwrite_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fwrite_005fname_005fnobody.reuse(_jspx_th_bean_005fwrite_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005fselect_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.apache.struts.taglib.html.SelectTag _jspx_th_html_005fselect_005f0 = (org.apache.struts.taglib.html.SelectTag) _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty_005fonchange.get(org.apache.struts.taglib.html.SelectTag.class);
    _jspx_th_html_005fselect_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fselect_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fselect_005f0.setProperty("category");
    _jspx_th_html_005fselect_005f0.setOnchange("loadSubCategoryData(this)");
    int _jspx_eval_html_005fselect_005f0 = _jspx_th_html_005fselect_005f0.doStartTag();
    if (_jspx_eval_html_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fselect_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fselect_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_html_005foption_005f0(_jspx_th_html_005fselect_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_html_005foptionsCollection_005f0(_jspx_th_html_005fselect_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fselect_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty_005fonchange.reuse(_jspx_th_html_005fselect_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty_005fonchange.reuse(_jspx_th_html_005fselect_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005foption_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fselect_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_html_005foption_005f0 = (org.apache.struts.taglib.html.OptionTag) _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_html_005foption_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005foption_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fselect_005f0);
    _jspx_th_html_005foption_005f0.setValue("0");
    int _jspx_eval_html_005foption_005f0 = _jspx_th_html_005foption_005f0.doStartTag();
    if (_jspx_eval_html_005foption_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005foption_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005foption_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005foption_005f0.doInitBody();
      }
      do {
        out.write("Select Category");
        int evalDoAfterBody = _jspx_th_html_005foption_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005foption_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005foption_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.reuse(_jspx_th_html_005foption_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.reuse(_jspx_th_html_005foption_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005foptionsCollection_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fselect_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:optionsCollection
    org.apache.struts.taglib.html.OptionsCollectionTag _jspx_th_html_005foptionsCollection_005f0 = (org.apache.struts.taglib.html.OptionsCollectionTag) _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.get(org.apache.struts.taglib.html.OptionsCollectionTag.class);
    _jspx_th_html_005foptionsCollection_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005foptionsCollection_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fselect_005f0);
    _jspx_th_html_005foptionsCollection_005f0.setName("UserPreferenceForm");
    _jspx_th_html_005foptionsCollection_005f0.setProperty("categoryList");
    _jspx_th_html_005foptionsCollection_005f0.setLabel("catName");
    _jspx_th_html_005foptionsCollection_005f0.setValue("catId");
    int _jspx_eval_html_005foptionsCollection_005f0 = _jspx_th_html_005foptionsCollection_005f0.doStartTag();
    if (_jspx_th_html_005foptionsCollection_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.reuse(_jspx_th_html_005foptionsCollection_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.reuse(_jspx_th_html_005foptionsCollection_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005fselect_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.apache.struts.taglib.html.SelectTag _jspx_th_html_005fselect_005f1 = (org.apache.struts.taglib.html.SelectTag) _005fjspx_005ftagPool_005fhtml_005fselect_005fsize_005fproperty_005fonchange_005fmultiple.get(org.apache.struts.taglib.html.SelectTag.class);
    _jspx_th_html_005fselect_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005fselect_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fselect_005f1.setProperty("subcategory");
    _jspx_th_html_005fselect_005f1.setOnchange("");
    _jspx_th_html_005fselect_005f1.setMultiple("true");
    _jspx_th_html_005fselect_005f1.setSize("5");
    int _jspx_eval_html_005fselect_005f1 = _jspx_th_html_005fselect_005f1.doStartTag();
    if (_jspx_eval_html_005fselect_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fselect_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fselect_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fselect_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<!-- ");
        if (_jspx_meth_html_005foption_005f1(_jspx_th_html_005fselect_005f1, _jspx_page_context))
          return true;
        out.write("  -->\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_html_005foptionsCollection_005f1(_jspx_th_html_005fselect_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fselect_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fselect_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fselect_005fsize_005fproperty_005fonchange_005fmultiple.reuse(_jspx_th_html_005fselect_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fselect_005fsize_005fproperty_005fonchange_005fmultiple.reuse(_jspx_th_html_005fselect_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005foption_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fselect_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_html_005foption_005f1 = (org.apache.struts.taglib.html.OptionTag) _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_html_005foption_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005foption_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fselect_005f1);
    _jspx_th_html_005foption_005f1.setValue("0");
    int _jspx_eval_html_005foption_005f1 = _jspx_th_html_005foption_005f1.doStartTag();
    if (_jspx_eval_html_005foption_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005foption_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005foption_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005foption_005f1.doInitBody();
      }
      do {
        out.write("Select SubCategories");
        int evalDoAfterBody = _jspx_th_html_005foption_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005foption_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005foption_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.reuse(_jspx_th_html_005foption_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.reuse(_jspx_th_html_005foption_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005foptionsCollection_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fselect_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:optionsCollection
    org.apache.struts.taglib.html.OptionsCollectionTag _jspx_th_html_005foptionsCollection_005f1 = (org.apache.struts.taglib.html.OptionsCollectionTag) _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.get(org.apache.struts.taglib.html.OptionsCollectionTag.class);
    _jspx_th_html_005foptionsCollection_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005foptionsCollection_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fselect_005f1);
    _jspx_th_html_005foptionsCollection_005f1.setName("UserPreferenceForm");
    _jspx_th_html_005foptionsCollection_005f1.setProperty("subcategoryList");
    _jspx_th_html_005foptionsCollection_005f1.setLabel("subCatName");
    _jspx_th_html_005foptionsCollection_005f1.setValue("subCatId");
    int _jspx_eval_html_005foptionsCollection_005f1 = _jspx_th_html_005foptionsCollection_005f1.doStartTag();
    if (_jspx_th_html_005foptionsCollection_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.reuse(_jspx_th_html_005foptionsCollection_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.reuse(_jspx_th_html_005foptionsCollection_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f0 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_005fsubmit_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005fsubmit_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fsubmit_005f0.setStyleClass("btn");
    _jspx_th_html_005fsubmit_005f0.setOnclick("SetActionSubmitForm('UserPreferenceForm', 'save')");
    int _jspx_eval_html_005fsubmit_005f0 = _jspx_th_html_005fsubmit_005f0.doStartTag();
    if (_jspx_eval_html_005fsubmit_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fsubmit_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fsubmit_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fsubmit_005f0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_bean_005fmessage_005f2(_jspx_th_html_005fsubmit_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fsubmit_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fsubmit_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fsubmit_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f0);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fsubmit_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f2 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fsubmit_005f0);
    _jspx_th_bean_005fmessage_005f2.setKey("label.common.html.select.button.savePreference");
    int _jspx_eval_bean_005fmessage_005f2 = _jspx_th_bean_005fmessage_005f2.doStartTag();
    if (_jspx_th_bean_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f1 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_005fsubmit_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005fsubmit_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fsubmit_005f1.setStyleClass("btn");
    _jspx_th_html_005fsubmit_005f1.setOnclick("SetActionSubmitForm('UserPreferenceForm', 'delete')");
    int _jspx_eval_html_005fsubmit_005f1 = _jspx_th_html_005fsubmit_005f1.doStartTag();
    if (_jspx_eval_html_005fsubmit_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fsubmit_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fsubmit_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fsubmit_005f1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_bean_005fmessage_005f3(_jspx_th_html_005fsubmit_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fsubmit_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fsubmit_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fsubmit_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f1);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fsubmit_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f3 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fsubmit_005f1);
    _jspx_th_bean_005fmessage_005f3.setKey("label.common.html.select.button.deletePreference");
    int _jspx_eval_bean_005fmessage_005f3 = _jspx_th_bean_005fmessage_005f3.doStartTag();
    if (_jspx_th_bean_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f3);
    return false;
  }

  private boolean _jspx_meth_html_005fselect_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:select
    org.apache.struts.taglib.html.SelectTag _jspx_th_html_005fselect_005f2 = (org.apache.struts.taglib.html.SelectTag) _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty.get(org.apache.struts.taglib.html.SelectTag.class);
    _jspx_th_html_005fselect_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005fselect_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fselect_005f2.setProperty("vicinitypolicyID");
    int _jspx_eval_html_005fselect_005f2 = _jspx_th_html_005fselect_005f2.doStartTag();
    if (_jspx_eval_html_005fselect_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fselect_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fselect_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fselect_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_html_005foption_005f2(_jspx_th_html_005fselect_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_html_005foptionsCollection_005f2(_jspx_th_html_005fselect_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fselect_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fselect_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fselect_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty.reuse(_jspx_th_html_005fselect_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fselect_005fproperty.reuse(_jspx_th_html_005fselect_005f2);
    return false;
  }

  private boolean _jspx_meth_html_005foption_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fselect_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:option
    org.apache.struts.taglib.html.OptionTag _jspx_th_html_005foption_005f2 = (org.apache.struts.taglib.html.OptionTag) _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.get(org.apache.struts.taglib.html.OptionTag.class);
    _jspx_th_html_005foption_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005foption_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fselect_005f2);
    _jspx_th_html_005foption_005f2.setValue("0");
    int _jspx_eval_html_005foption_005f2 = _jspx_th_html_005foption_005f2.doStartTag();
    if (_jspx_eval_html_005foption_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005foption_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005foption_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005foption_005f2.doInitBody();
      }
      do {
        out.write("Use System Default");
        int evalDoAfterBody = _jspx_th_html_005foption_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005foption_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005foption_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.reuse(_jspx_th_html_005foption_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005foption_005fvalue.reuse(_jspx_th_html_005foption_005f2);
    return false;
  }

  private boolean _jspx_meth_html_005foptionsCollection_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fselect_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:optionsCollection
    org.apache.struts.taglib.html.OptionsCollectionTag _jspx_th_html_005foptionsCollection_005f2 = (org.apache.struts.taglib.html.OptionsCollectionTag) _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.get(org.apache.struts.taglib.html.OptionsCollectionTag.class);
    _jspx_th_html_005foptionsCollection_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005foptionsCollection_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fselect_005f2);
    _jspx_th_html_005foptionsCollection_005f2.setName("UserPreferenceForm");
    _jspx_th_html_005foptionsCollection_005f2.setProperty("vicinityPolicyList");
    _jspx_th_html_005foptionsCollection_005f2.setLabel("vicinityName");
    _jspx_th_html_005foptionsCollection_005f2.setValue("vicinityID");
    int _jspx_eval_html_005foptionsCollection_005f2 = _jspx_th_html_005foptionsCollection_005f2.doStartTag();
    if (_jspx_th_html_005foptionsCollection_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.reuse(_jspx_th_html_005foptionsCollection_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005foptionsCollection_005fvalue_005fproperty_005fname_005flabel_005fnobody.reuse(_jspx_th_html_005foptionsCollection_005f2);
    return false;
  }

  private boolean _jspx_meth_html_005ftext_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:text
    org.apache.struts.taglib.html.TextTag _jspx_th_html_005ftext_005f0 = (org.apache.struts.taglib.html.TextTag) _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_html_005ftext_005f0.setPageContext(_jspx_page_context);
    _jspx_th_html_005ftext_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005ftext_005f0.setProperty("startDate");
    _jspx_th_html_005ftext_005f0.setSize("17");
    _jspx_th_html_005ftext_005f0.setMaxlength("16");
    int _jspx_eval_html_005ftext_005f0 = _jspx_th_html_005ftext_005f0.doStartTag();
    if (_jspx_th_html_005ftext_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.reuse(_jspx_th_html_005ftext_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.reuse(_jspx_th_html_005ftext_005f0);
    return false;
  }

  private boolean _jspx_meth_html_005ftext_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:text
    org.apache.struts.taglib.html.TextTag _jspx_th_html_005ftext_005f1 = (org.apache.struts.taglib.html.TextTag) _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.get(org.apache.struts.taglib.html.TextTag.class);
    _jspx_th_html_005ftext_005f1.setPageContext(_jspx_page_context);
    _jspx_th_html_005ftext_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005ftext_005f1.setProperty("endDate");
    _jspx_th_html_005ftext_005f1.setSize("17");
    _jspx_th_html_005ftext_005f1.setMaxlength("16");
    int _jspx_eval_html_005ftext_005f1 = _jspx_th_html_005ftext_005f1.doStartTag();
    if (_jspx_th_html_005ftext_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.reuse(_jspx_th_html_005ftext_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005ftext_005fsize_005fproperty_005fmaxlength_005fnobody.reuse(_jspx_th_html_005ftext_005f1);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f9 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f9.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005flink_005f9.setAction("UserLocation.do?formActionOriginatedFrom=UserPreferenceModule");
    int _jspx_eval_html_005flink_005f9 = _jspx_th_html_005flink_005f9.doStartTag();
    if (_jspx_eval_html_005flink_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f9.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f9.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t                     <h3>");
        if (_jspx_meth_bean_005fmessage_005f4(_jspx_th_html_005flink_005f9, _jspx_page_context))
          return true;
        out.write("</h3>\r\n");
        out.write("\t\t        ");
        int evalDoAfterBody = _jspx_th_html_005flink_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f9 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005faction.reuse(_jspx_th_html_005flink_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005faction.reuse(_jspx_th_html_005flink_005f9);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f4 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f9);
    _jspx_th_bean_005fmessage_005f4.setKey("label.common.html.select.button.location");
    int _jspx_eval_bean_005fmessage_005f4 = _jspx_th_bean_005fmessage_005f4.doStartTag();
    if (_jspx_th_bean_005fmessage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f4);
    return false;
  }

  private boolean _jspx_meth_html_005flink_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_005flink_005f10 = (org.apache.struts.taglib.html.LinkTag) _005fjspx_005ftagPool_005fhtml_005flink_005faction.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_005flink_005f10.setPageContext(_jspx_page_context);
    _jspx_th_html_005flink_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005flink_005f10.setAction("UserLocation.do?formActionOriginatedFrom=UserPreferenceModule");
    int _jspx_eval_html_005flink_005f10 = _jspx_th_html_005flink_005f10.doStartTag();
    if (_jspx_eval_html_005flink_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005flink_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005flink_005f10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005flink_005f10.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<h3>");
        if (_jspx_meth_bean_005fmessage_005f5(_jspx_th_html_005flink_005f10, _jspx_page_context))
          return true;
        out.write("</h3>\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005flink_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005flink_005f10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005flink_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005flink_005faction.reuse(_jspx_th_html_005flink_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005flink_005faction.reuse(_jspx_th_html_005flink_005f10);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005flink_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f5 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f5.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f10);
    _jspx_th_bean_005fmessage_005f5.setKey("label.common.html.select.button.location");
    int _jspx_eval_bean_005fmessage_005f5 = _jspx_th_bean_005fmessage_005f5.doStartTag();
    if (_jspx_th_bean_005fmessage_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f5);
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f2 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_005fsubmit_005f2.setPageContext(_jspx_page_context);
    _jspx_th_html_005fsubmit_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fsubmit_005f2.setStyleClass("btn");
    _jspx_th_html_005fsubmit_005f2.setOnclick("SetActionSubmitForm('UserPreferenceForm', 'recommend')");
    int _jspx_eval_html_005fsubmit_005f2 = _jspx_th_html_005fsubmit_005f2.doStartTag();
    if (_jspx_eval_html_005fsubmit_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fsubmit_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fsubmit_005f2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fsubmit_005f2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_bean_005fmessage_005f6(_jspx_th_html_005fsubmit_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fsubmit_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fsubmit_005f2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fsubmit_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f2);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fsubmit_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f6 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f6.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fsubmit_005f2);
    _jspx_th_bean_005fmessage_005f6.setKey("label.common.html.select.button.recommend");
    int _jspx_eval_bean_005fmessage_005f6 = _jspx_th_bean_005fmessage_005f6.doStartTag();
    if (_jspx_th_bean_005fmessage_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f6);
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f3 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_005fsubmit_005f3.setPageContext(_jspx_page_context);
    _jspx_th_html_005fsubmit_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fsubmit_005f3.setStyleClass("btn");
    _jspx_th_html_005fsubmit_005f3.setOnclick("SetActionSubmitForm('UserPreferenceForm', 'hideRecommend')");
    int _jspx_eval_html_005fsubmit_005f3 = _jspx_th_html_005fsubmit_005f3.doStartTag();
    if (_jspx_eval_html_005fsubmit_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fsubmit_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fsubmit_005f3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fsubmit_005f3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_bean_005fmessage_005f7(_jspx_th_html_005fsubmit_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fsubmit_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fsubmit_005f3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fsubmit_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f3);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fsubmit_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f7 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f7.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fsubmit_005f3);
    _jspx_th_bean_005fmessage_005f7.setKey("label.common.html.select.button.recommend.hide");
    int _jspx_eval_bean_005fmessage_005f7 = _jspx_th_bean_005fmessage_005f7.doStartTag();
    if (_jspx_th_bean_005fmessage_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f7);
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f4 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_005fsubmit_005f4.setPageContext(_jspx_page_context);
    _jspx_th_html_005fsubmit_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fsubmit_005f4.setStyleClass("btn");
    _jspx_th_html_005fsubmit_005f4.setOnclick("SetActionSubmitForm('UserPreferenceForm', 'recommend')");
    int _jspx_eval_html_005fsubmit_005f4 = _jspx_th_html_005fsubmit_005f4.doStartTag();
    if (_jspx_eval_html_005fsubmit_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fsubmit_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fsubmit_005f4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fsubmit_005f4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_bean_005fmessage_005f8(_jspx_th_html_005fsubmit_005f4, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fsubmit_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fsubmit_005f4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fsubmit_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f4);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fsubmit_005f4, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f8 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f8.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fsubmit_005f4);
    _jspx_th_bean_005fmessage_005f8.setKey("label.common.html.select.button.recommend");
    int _jspx_eval_bean_005fmessage_005f8 = _jspx_th_bean_005fmessage_005f8.doStartTag();
    if (_jspx_th_bean_005fmessage_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f8);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f9 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f9.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_bean_005fmessage_005f9.setKey("label.UserPreferenceAction.message.preferences.list");
    int _jspx_eval_bean_005fmessage_005f9 = _jspx_th_bean_005fmessage_005f9.doStartTag();
    if (_jspx_th_bean_005fmessage_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f9);
    return false;
  }

  private boolean _jspx_meth_html_005fsubmit_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fform_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_005fsubmit_005f5 = (org.apache.struts.taglib.html.SubmitTag) _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_005fsubmit_005f5.setPageContext(_jspx_page_context);
    _jspx_th_html_005fsubmit_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fform_005f0);
    _jspx_th_html_005fsubmit_005f5.setStyleClass("btn");
    _jspx_th_html_005fsubmit_005f5.setOnclick("SetActionSubmitForm('UserPreferenceForm', 'saveLevels')");
    int _jspx_eval_html_005fsubmit_005f5 = _jspx_th_html_005fsubmit_005f5.doStartTag();
    if (_jspx_eval_html_005fsubmit_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_005fsubmit_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_005fsubmit_005f5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_005fsubmit_005f5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_bean_005fmessage_005f10(_jspx_th_html_005fsubmit_005f5, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_html_005fsubmit_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_005fsubmit_005f5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_005fsubmit_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fhtml_005fsubmit_005fstyleClass_005fonclick.reuse(_jspx_th_html_005fsubmit_005f5);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fsubmit_005f5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f10 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f10.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fsubmit_005f5);
    _jspx_th_bean_005fmessage_005f10.setKey("label.common.html.select.button.save.levels");
    int _jspx_eval_bean_005fmessage_005f10 = _jspx_th_bean_005fmessage_005f10.doStartTag();
    if (_jspx_th_bean_005fmessage_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f10);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f11 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f11.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f11.setKey("label.UserPreferenceAction.message.recos.list");
    int _jspx_eval_bean_005fmessage_005f11 = _jspx_th_bean_005fmessage_005f11.doStartTag();
    if (_jspx_th_bean_005fmessage_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f11);
    return false;
  }

  private boolean _jspx_meth_bean_005fmessage_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_html_005fhtml_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  bean:message
    org.apache.struts.taglib.bean.MessageTag _jspx_th_bean_005fmessage_005f12 = (org.apache.struts.taglib.bean.MessageTag) _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.get(org.apache.struts.taglib.bean.MessageTag.class);
    _jspx_th_bean_005fmessage_005f12.setPageContext(_jspx_page_context);
    _jspx_th_bean_005fmessage_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005fhtml_005f0);
    _jspx_th_bean_005fmessage_005f12.setKey("Info.UserPreferenceAction.Recommendation.none");
    int _jspx_eval_bean_005fmessage_005f12 = _jspx_th_bean_005fmessage_005f12.doStartTag();
    if (_jspx_th_bean_005fmessage_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fbean_005fmessage_005fkey_005fnobody.reuse(_jspx_th_bean_005fmessage_005f12);
    return false;
  }
}
