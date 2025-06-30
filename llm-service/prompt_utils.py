# prompt_utils.py

def format_task_prompt(title, description):
    return f"""
You are a smart and helpful project assistant.

Here is a task:
- Title: {title}
- Description: {description}

Please suggest the next actionable step or give a recommendation that helps progress this task.
Respond in 1–2 sentences.
"""
