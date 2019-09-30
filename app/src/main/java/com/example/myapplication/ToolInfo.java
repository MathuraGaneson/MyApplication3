package com.example.myapplication;

import android.renderscript.Sampler;

public class ToolInfo {

  private String Barcode;
  private String statusid;
  private String toolspec;
  private String toolgroup;
  private String processid;
  private String lastpmdate;
  private String barcodename;
  private String vendorname;
  private String pmid;
  private String rack;
  private String row;
  private String section;

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getToolspec() {
        return toolspec;
    }

    public void setToolspec(String toolspec) {
        this.toolspec = toolspec;
    }

    public String getToolgroup() {
        return toolgroup;
    }

    public void setToolgroup(String toolgroup) {
        this.toolgroup = toolgroup;
    }

    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public String getLastpmdate() {
        return lastpmdate;
    }

    public void setLastpmdate(String lastpmdate) {
        this.lastpmdate = lastpmdate;
    }

    public String getBarcodename() {
        return barcodename;
    }

    public void setBarcodename(String barcodename) {
        this.barcodename = barcodename;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
