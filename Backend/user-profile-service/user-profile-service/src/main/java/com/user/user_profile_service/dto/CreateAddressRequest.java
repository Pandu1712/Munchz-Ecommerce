package com.user.user_profile_service.dto;

public record CreateAddressRequest( String label,
                                    String addressLine1,
                                    String addressLine2,
                                    String city,
                                    String state,
                                    String country,
                                    String pincode,
                                    String phone,
                                    boolean isDefault) {
}
