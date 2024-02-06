package com.sap.cmoplatform.objects;

public class BlockChainExplorer {

    private String disposition;
    private String timestamp;
    private String action;
    private String gln;
    private String writtenToBlockchain;


    public BlockChainExplorer() {
    }

    public String getDisposition() {
        return disposition;
    }

    public String getTimeStamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public String getGln() {
        return gln;
    }

    public String getwrittenToBlockchain() {
        return writtenToBlockchain;
    }

    public BlockChainExplorer withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public BlockChainExplorer withAction(String action) {
        this.action = action;
        return this;
    }

    public BlockChainExplorer withGLN(String gln) {
        this.gln = gln;
        return this;
    }

    public BlockChainExplorer withWrittenToBlockchain(String writtenToBlockchain) {
        this.writtenToBlockchain = writtenToBlockchain;
        return this;
    }

    public BlockChainExplorer withDisposition(String disposition) {
        this.disposition = disposition;
        return this;
    }

}
