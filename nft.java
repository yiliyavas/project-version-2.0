public class nft {
    private String nftid;
    private String nftName;
    private String url;
    private String creator;
    private String owner;
    private String mintTime;
    private String description;
    
    public nft() {}

    public nft(String nftid, String name, String url, String creator, String owner, String mintTime, String description) {
        this.nftid = nftid;
        this.nftName = name;
        this.url = url;
        this.creator = creator;
        this.owner = owner;
        this.mintTime = mintTime;
        this.description = description;
    }

    public String getNftid() {
        return nftid;
    }

    public void setNftid(String nftid) {
        this.nftid = nftid;
    }

    public String getName() {
        return nftName;
    }

    public void setName(String name) {
        this.nftName = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMintTime() {
        return mintTime;
    }

    public void setMintTime(String mintTime) {
        this.mintTime = mintTime;
    }
    
    public String getDescription() {
        return description;
    }


	public void setDescription(String description) {
		this.description = description;
		
	}
}