import os

def merge_text_files_in_directory(output_file):
    input_files = [file for file in os.listdir('.') if file.endswith('.java')]
    with open(output_file, 'w') as output:
        for file_name in input_files:
            with open(file_name, 'r') as file:
                output.write(file.read())
                output.write('\n')  # Add a new line after each file's content

# Usage example
output_file = 'merged.txt'
merge_text_files_in_directory(output_file)
