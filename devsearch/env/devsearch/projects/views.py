from django.shortcuts import render, redirect
from django.contrib.auth.decorators import login_required
from django.http import HttpResponse
from django.contrib import messages
from .models import Project
from .forms import ProjectForm, ReviewForm
from .utils import searchProjects, paginateProjects

# Create your views here.


def projects(request):
    
    projects, search_query = searchProjects(request)

    custom_range, projects, paginator = paginateProjects(request, projects, 3) 

    context = {"projects": projects, 
               "search_query": search_query, 
               "paginator": paginator,
               "custom_range": custom_range}
    return render(request, 'projects/projects.html', context)


def project(request, pk):
    projectObj = Project.objects.get(id=pk) # get
    form = ReviewForm()

    if request.method == 'POST':
        form = ReviewForm(request.POST)
        review = form.save(commit=False)
        review.project = projectObj
        review.owner = request.user.profile
        review.save()
        projectObj.getVoteCount
        messages.success(request, f"Review added successfully!")
        return redirect('project', pk=projectObj.id)

    return render(request, 'projects/single-project.html', {'project': projectObj, 'form': form})
 
@login_required(login_url="login")
def createProject(request):
    profile = request.user.profile
    form = ProjectForm()
    if request.method == "POST":
        form = ProjectForm(request.POST, request.FILES)
        if form.is_valid():
            project = form.save(commit=False)
            project.owner = profile
            project.save()
            return redirect('account')
    context = {'form': form}
    return render(request, "projects/project_form.html", context)


@login_required(login_url="login")
def updateProject(request, pk):
    profile = request.user.profile
    project = profile.project_set.get(id=pk)
    form = ProjectForm(instance=project)
    if request.method == "POST":
        form = ProjectForm(request.POST, request.FILES, instance=project)
        if form.is_valid():
            form.save()
            return redirect('account')
    context = {'form': form}
    return render(request, "projects/project_form.html", context)



@login_required(login_url="login")
def deleteProject(request, pk):
    profile = request.user.profile
    project = profile.project_set.get(id=pk)
    if request.method == "POST":
        project.delete()
        return redirect('account')
    context = {'object' : project}
    return render(request, 'projects/delete_template.html', context)