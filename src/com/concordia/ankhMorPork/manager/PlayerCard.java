package com.concordia.ankhMorPork.manager;

public class PlayerCard {

		Integer identifier;
		public Integer getIdentifier() {
			return identifier;
		}
		public void setIdentifier(Integer identifier) {
			this.identifier = identifier;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String[] getActionItem() {
			return actionItem;
		}
		public void setActionItem(String[] actionItem) {
			this.actionItem = actionItem;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		String name;
		String[] actionItem;
		String description;
		
		
		
}
