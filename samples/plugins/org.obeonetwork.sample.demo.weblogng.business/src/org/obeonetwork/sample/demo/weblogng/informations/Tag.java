package org.obeonetwork.sample.demo.weblogng.informations;

// Start of user code for imports
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;	
import org.obeonetwork.sample.demo.weblogng.blog.BlogEntry;

// End of user code for imports

/**
 * 
 */
public class Tag implements Serializable {

	/**
     * serialVersionUID is used for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Constant representing the name of the automatic primary key field.
     */
	public static final String PROP_ID = "id";
	
	/**
     * Constant representing the name of the field isTagOf.
     */
	public static final String PROP_ISTAGOF = "isTagOf";
	
	/**
     * Constant representing the name of the field name.
     */
	public static final String PROP_NAME = "name";
	
    /**
     * Automatic primary key.
     */
    private String id;
    
    /**
     * Field isTagOf.
     */
	protected Collection<BlogEntry> isTagOf;

    /**
     * Field name.
     */
	protected String name;

	/**
	 * Default constructor.
	 */
	public Tag() {
		super();
		this.isTagOf = new HashSet<BlogEntry>();
	}
	
	/**
	 * Return the identifier.
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Set a value to parameter id.
	 * @param id Value of the identifier.
	 */
	public void setId(final String id) {
		this.id = id;
	}	

	/**
	 * Constructor with all parameters initialized.
	 * @param isTagOf. 
	 * @param name. 
	 */
	public Tag(Collection<BlogEntry> isTagOf, String name) {
		this();
		this.isTagOf.addAll(isTagOf);
		this.name = name;
	}

	/**
	 * Return name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set a value to parameter name.
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Return isTagOf.
	 * @return isTagOf
	 */
	public Collection<BlogEntry> getIsTagOf() {
		return isTagOf;
	}

	/**
	 * Set a value to parameter isTagOf.
	 * @param isTagOf
	 */
	public void setIsTagOf(final Collection<BlogEntry> isTagOf) {
		this.isTagOf = isTagOf;
	}
	
	/**
	 * Add a isTagOf to the isTagOf collection.
	 * Birectional association : add the current Tag instance to given isTagOf parameter.
	 * @param isTagOfElt Element to add.
	 */
	 public void addIsTagOf(final BlogEntry isTagOfElt) {
	 	this.isTagOf.add(isTagOfElt);
	 	isTagOfElt.getTags().add(this);
	 }
	 
	/**
	 * Remove a isTagOf to the isTagOf collection.
	 * Birectionnal association : remove the current Tag instance to given isTagOf parameter.
	 * @param isTagOfElt Element to remove.
	 */
	 public void removeIsTagOf(final BlogEntry isTagOfElt) {
	 	this.isTagOf.remove(isTagOfElt);
	 	isTagOfElt.getTags().remove(this);
	 }

	/** 
	 * Equality test based on identifiers.
	 * @param value Value to compare.
	 * @return Returns true if and only if given object is an instance of
     * Tag and the given object has the same PK as this
     * if the PK is not null or their ids are equal.
	 */
	public boolean equals(final Object other) {
	 	// Start of user code for equals
		if (this == other) {
			return true;
		}
		if (id==null) {
			return false;
		}
		if (!(other instanceof Tag)) {
			return false;
		}
		
		final Tag castedOther = (Tag) other;
		if (id != null && castedOther.getId() != null) {
			return id.equals(castedOther.getId());
		}
		return true;
		
		 // End of user code for equals
	}

	/** 
	 * HashTable code based on identifier hash codes.
	 * @return hash value.
	 */
	public int hashCode() {
		return id==null ? System.identityHashCode(this) : id.hashCode();
	}
   
	// Start of user code for private methods
	// TODO Remove this line and add your private methods here
	// End of user code for private methods
   
}
