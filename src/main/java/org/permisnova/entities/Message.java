/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.permisnova.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author vanbritt
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    private Integer date;
    @Size(max = 254)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "sender", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser sender;
     @JoinColumn(name = "receiver", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser receiver;
    @OneToMany(mappedBy = "messageId", fetch = FetchType.LAZY)
    private List<Message> messageList;
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Message messageId;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AppUser getSender() {
        return sender;
    }

    public void setSender(AppUser sender) {
        this.sender = sender;
    }

    public AppUser getReceiver() {
        return receiver;
    }

    public void setReceiver(AppUser receiver) {
        this.receiver = receiver;
    }

   

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Message getMessageId() {
        return messageId;
    }

    public void setMessageId(Message messageId) {
        this.messageId = messageId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.test.entities.Message[ id=" + id + " ]";
    }
    
}
