package com.collaboration.dao;

import java.util.List;

import com.collaboration.models.Job;

public interface JobDao 
{
void addJob(Job job);
List<Job> getalljobs();

}
