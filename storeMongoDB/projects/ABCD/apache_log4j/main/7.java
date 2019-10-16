  String getFooter() {
    StringBuffer sbuf = new StringBuffer();
    sbuf.append("</table>" + Layout.LINE_SEP);
    sbuf.append("<br>" + Layout.LINE_SEP);
    sbuf.append("</body></html>");
    return sbuf.toString();
  }
